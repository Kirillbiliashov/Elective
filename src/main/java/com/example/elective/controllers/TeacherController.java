package com.example.elective.controllers;

import com.example.elective.dto.JournalDTO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Role;
import com.example.elective.selection.CoursePagination;
import com.example.elective.selection.Pagination;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TeacherService;
import com.example.elective.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.PaginationUtils.setPageAttributes;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

  @Autowired
  private TeacherService teacherService;
  @Autowired
  private JournalService journalService;
  @Autowired
  private AccountService accountService;

  @GetMapping()
  public String teachersList(HttpServletRequest req, Model model) {
    int page = PaginationUtils.getPageNumber(req);
    int displayCount = PaginationUtils.getItemsPerPage(req);
    long total = accountService.getTotalCount(Role.TEACHER);
    Pagination pagination = new Pagination(page, displayCount, total);
    PaginationUtils.setPageAttributes(req, pagination.getPage());
    model.addAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
    model.addAttribute(TEACHERS_ATTR,
        accountService.getPaginatedTeachers(pagination));
    return "teachers";
  }

  @GetMapping("/{id}")
  public String teacherPage(@PathVariable("id") int id,
                            Model model, HttpServletRequest req) {
    int page = PaginationUtils.getPageNumber(req);
    setPageAttributes(req, page);
    long total = teacherService.getCoursesCount(id);
    Pagination pagination = new CoursePagination(page, total);
    setPageAttributes(req, pagination.getPage());
    model.addAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
    Optional<Course> optCourse = teacherService.findCourse(id, pagination);
    optCourse.ifPresent(course -> setAttributes(course, model));
    return "teacher";
  }

  private void setAttributes(Course course, Model model) {
    List<JournalDTO> dtoList = teacherService.getJournalList(course.getId());
    model.addAttribute(JOURNALS_ATTR, dtoList);
    model.addAttribute(COURSE_ATTR, course);
    model.addAttribute(CURR_DATE_ATTR, CURRENT_DATE);
  }

  @GetMapping("/register")
  public String teacherRegistrationForm(Model model) {
    model.addAttribute("teacher", new Account());
    return "teacherRegistration";
  }

  @PostMapping("/register")
  public String registerTeacher(@ModelAttribute("teacher") Account teacher) {
    teacherService.save(teacher);
    return "redirect:";
  }

  @PostMapping("/addGrade/{id}")
  public String addGrade(@PathVariable("id") int id,
                         @RequestParam("grade") int grade) {
    journalService.updateGrade(id, grade);
    return "redirect:teacher";
  }

}
