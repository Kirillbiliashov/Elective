package com.example.elective.controllers;

import com.example.elective.annotations.PageParam;
import com.example.elective.dto.JournalDTO;
import com.example.elective.dto.TeacherCourseDTO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.models.Role;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TeacherService;
import com.example.elective.utils.Pagination;
import com.example.elective.utils.PaginationUtils;
import com.example.elective.utils.SecurityUtils;
import com.example.elective.validator.AccountValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.elective.utils.Constants.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

  private final TeacherService teacherService;
  private final JournalService journalService;
  private final AccountService accountService;
  private final SecurityUtils securityUtils;
  private final AccountValidator accountValidator;
  private final ModelMapper modelMapper;
  private final PaginationUtils paginationUtils;

  @Autowired
  public TeacherController(TeacherService teacherService,
                           JournalService journalService,
                           AccountService accountService,
                           SecurityUtils securityUtils,
                           AccountValidator accountValidator,
                           ModelMapper modelMapper,
                           PaginationUtils paginationUtils) {
    this.teacherService = teacherService;
    this.journalService = journalService;
    this.accountService = accountService;
    this.securityUtils = securityUtils;
    this.accountValidator = accountValidator;
    this.modelMapper = modelMapper;
    this.paginationUtils = paginationUtils;
  }

  @GetMapping()
  public String teachersList(@PageParam Pagination pagination, Model model) {
    Page<Account> pageInfo = accountService.getAll(Role.ROLE_TEACHER, pagination);
    paginationUtils.setPaginationAttributes(model, pageInfo);
    model.addAttribute(TEACHERS_ATTR, pageInfo.getContent());
    return TEACHERS_PAGE;
  }

  @GetMapping("/teacher")
  public String teacherPage(
      @RequestParam(value = PAGE_PARAM, required = false) Integer page, Model model) {
    int id = securityUtils.getUserId();
    Page<Course> pageInfo = teacherService.findCourse(id, page);
    model.addAttribute(COURSES_ATTR, convert(pageInfo.getContent()));
    paginationUtils.setPaginationAttributes(model, pageInfo);
    model.addAttribute(CURR_DATE_ATTR, CURRENT_DATE);
    return TEACHER_PAGE;
  }

  private List<TeacherCourseDTO> convert(List<Course> courses) {
    return courses
        .stream()
        .map(course -> modelMapper.map(course, TeacherCourseDTO.class)
            .setJournalList(getJournalDTOList(course.getStudents())))
        .toList();
  }

  private List<JournalDTO> getJournalDTOList(List<Journal> journalList) {
    return journalList
        .stream()
        .map(journal -> modelMapper.map(journal, JournalDTO.class))
        .toList();
  }

  @GetMapping("/register")
  public String teacherRegistrationForm(@ModelAttribute(TEACHER_PARAM) Account teacher) {
    return TEACHER_REGISTRATION_PAGE;
  }

  @PostMapping("/register")
  public String registerTeacher(@ModelAttribute(TEACHER_PARAM) @Valid Account teacher,
                                BindingResult result) {
    accountValidator.validate(teacher, result);
    if (result.hasErrors()) return TEACHER_REGISTRATION_PAGE;
    teacherService.save(teacher);
    return "redirect:../";
  }

  @PostMapping("/addGrade/{id}")
  public String addGrade(@PathVariable("id") int id,
                         @RequestParam(GRADE_PARAM) int grade) {
    journalService.updateGrade(id, grade);
    return "redirect:../teacher";
  }

}
