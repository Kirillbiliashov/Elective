package com.example.elective.controllers;

import com.example.elective.annotations.PageParam;
import com.example.elective.dto.StudentDTO;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.StudentService;
import com.example.elective.utils.Pagination;
import com.example.elective.utils.PaginationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.elective.utils.Constants.*;

@Controller
@RequestMapping("/students")
public class StudentController {

  private final AccountService accountService;
  private final StudentService studentService;
  private final ModelMapper modelMapper;
  private final PaginationUtils utils;

  @Autowired
  public StudentController(AccountService accountService,
                           StudentService studentService,
                           ModelMapper modelMapper, PaginationUtils utils) {
    this.accountService = accountService;
    this.studentService = studentService;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @GetMapping
  public String studentsList(@PageParam Pagination pagination, Model model) {
    Page<Account> pageInfo = accountService.getAll(Role.ROLE_STUDENT, pagination);
    utils.setPaginationAttributes(model, pageInfo);
    model.addAttribute(STUDENTS_ATTR, convert(pageInfo.getContent()));
    return STUDENTS_PAGE;
  }

  private List<StudentDTO> convert(List<Account> students) {
    return students
        .stream()
        .map(st -> modelMapper.map(st, StudentDTO.class))
        .toList();
  }

  @PostMapping("/changeBlock/{id}")
  public String changeBlock(@PathVariable("id") int id) {
    studentService.changeBlockStatus(id);
    return "redirect:../";
  }

}
