package com.example.elective.controllers;

import com.example.elective.mappers.dtoMappers.StudentDTOMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.selection.Pagination;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.StudentService;
import com.example.elective.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
  public String studentsList(Model model, HttpServletRequest req) {
    int page = PaginationUtils.getPageNumber(req);
    int displayCount = PaginationUtils.getItemsPerPage(req);
    long total = accountService.getTotalCount(Role.STUDENT);
    Pagination pagination = new Pagination(page, displayCount, total);
    PaginationUtils.setPageAttributes(req, pagination.getPage());
    model.addAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
    List<Account> students = accountService.getPaginatedStudents(pagination);
    model.addAttribute(STUDENTS_ATTR,
        students.stream().map(studentMapper::map).toList());
    return "students";
  }

  @PostMapping("/changeBlock/{id}")
  public String changeBlock(@PathVariable("id") int id) {
    System.out.println("id: " + id);
    studentService.changeBlockStatus(id);
    return "redirect:../";
  }

}
