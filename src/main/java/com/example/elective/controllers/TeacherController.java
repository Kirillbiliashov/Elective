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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.elective.utils.Constants.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

  @Autowired
  private TeacherService teacherService;
  @Autowired
  private JournalService journalService;
  @Autowired
  private AccountService accountService;
  @Autowired
  private SecurityUtils securityUtils;

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
    model.addAttribute("courses",
        getTeacherCourseDTOList(pageInfo.getContent()));
    model.addAttribute("page", pageInfo.getNumber());
    model.addAttribute("pages", pageInfo.getTotalElements());
    model.addAttribute(CURR_DATE_ATTR, CURRENT_DATE);
    return "teachers/teacher";
  }

  private List<TeacherCourseDTO> getTeacherCourseDTOList(List<Course> courses) {
    return courses
        .stream()
        .map(course -> new TeacherCourseDTO()
            .setName(course.getName())
            .setStartDate(course.getStartDate())
            .setEndDate(course.getEndDate())
            .setTeacherId(course.getTeacher().getId())
            .setJournalList(getJournalDTOList(course.getStudents())))
        .toList();
  }

  private List<JournalDTO> getJournalDTOList(List<Journal> journalList) {
    return journalList
        .stream()
        .map(journal -> new JournalDTO()
            .setId(journal.getId())
            .setStudent(journal.getStudent().getFullName())
            .setGrade(journal.getGrade()))
        .toList();
  }

  @GetMapping("/register")
  public String teacherRegistrationForm(@ModelAttribute("teacher") Account teacher) {
    return "teachers/registration";
  }

  @PostMapping("/register")
  public String registerTeacher(@ModelAttribute("teacher") Account teacher) {
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
