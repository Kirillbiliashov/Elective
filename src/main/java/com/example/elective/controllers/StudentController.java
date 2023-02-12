package com.example.elective.controllers;

import com.example.elective.mappers.dtoMappers.StudentDTOMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.elective.utils.Constants.*;

@Controller
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private StudentDTOMapper studentMapper;
  @Autowired
  private StudentService studentService;

  @GetMapping
  public String studentsList(Model model,
                             @RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size) {
    Page<Account> pageInfo = accountService.getAll(Role.ROLE_STUDENT, page, size);
    model.addAttribute("pages", pageInfo.getTotalPages());
    model.addAttribute("page", pageInfo.getNumber());
    model.addAttribute(STUDENTS_ATTR,
        pageInfo.getContent().stream().map(studentMapper::map).toList());
    return "students/all";
  }

  @PostMapping("/changeBlock/{id}")
  public String changeBlock(@PathVariable("id") int id) {
    studentService.changeBlockStatus(id);
    return "redirect:../";
  }

}
