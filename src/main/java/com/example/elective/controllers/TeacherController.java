package com.example.elective.controllers;

import com.example.elective.dto.JournalDTO;
import com.example.elective.dto.TeacherCourseDTO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.models.Role;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TeacherService;
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

  @Autowired
  public TeacherController(TeacherService teacherService,
                           JournalService journalService,
                           AccountService accountService,
                           SecurityUtils securityUtils,
                           AccountValidator accountValidator,
                           ModelMapper modelMapper) {
    this.teacherService = teacherService;
    this.journalService = journalService;
    this.accountService = accountService;
    this.securityUtils = securityUtils;
    this.accountValidator = accountValidator;
    this.modelMapper = modelMapper;
  }

  @GetMapping()
  public String teachersList(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size, Model model) {
    Page<Account> pageInfo =  accountService.getAll(Role.ROLE_TEACHER, page, size);
    model.addAttribute("pages", pageInfo.getTotalPages());
    model.addAttribute("page", pageInfo.getNumber());
    model.addAttribute("size", pageInfo.getTotalElements());
    model.addAttribute(TEACHERS_ATTR, pageInfo.getContent());
    return "teachers/all";
  }

  @GetMapping("/teacher")
  public String teacherPage(Model model,
                            @RequestParam(value = "page",
                                required = false) Integer page) {
    int id = securityUtils.getUserId();
    Page<Course> pageInfo = teacherService.findCourse(id, page);
    model.addAttribute("courses", getTeacherCourseDTOList(pageInfo.getContent()));
    model.addAttribute("page", pageInfo.getNumber());
    model.addAttribute("pages", pageInfo.getTotalElements());
    model.addAttribute(CURR_DATE_ATTR, CURRENT_DATE);
    return "teachers/teacher";
  }

  private List<TeacherCourseDTO> getTeacherCourseDTOList(List<Course> courses) {
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
  public String teacherRegistrationForm(@ModelAttribute("teacher") Account teacher) {
    return "teachers/registration";
  }

  @PostMapping("/register")
  public String registerTeacher(@ModelAttribute("teacher") @Valid Account teacher,
                                BindingResult result) {
    accountValidator.validate(teacher, result);
    if (result.hasErrors()) return "teachers/registration";
    teacherService.save(teacher);
    return "redirect:../";
  }

  @PostMapping("/addGrade/{id}")
  public String addGrade(@PathVariable("id") int id,
                         @RequestParam("grade") int grade) {
    journalService.updateGrade(id, grade);
    return "teachers/teacher";
  }

}
