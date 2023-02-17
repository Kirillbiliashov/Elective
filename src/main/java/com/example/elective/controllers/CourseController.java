package com.example.elective.controllers;

import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.models.*;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.CourseService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TopicService;
import com.example.elective.utils.CourseSelection;
import com.example.elective.utils.SecurityUtils;
import com.example.elective.validator.CourseValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.Constants.TEACHERS_ATTR;

@Controller
@RequestMapping("/courses")
public class CourseController {

  private final CourseService courseService;
  private final AccountService accountService;
  private final TopicService topicService;
  private final JournalService journalService;
  private final SecurityUtils securityUtils;
  private final CourseValidator courseValidator;
  private final ModelMapper modelMapper;

  @Autowired
  public CourseController(CourseService courseService, AccountService accountService,
                          TopicService topicService, CourseValidator courseValidator,
                          SecurityUtils securityUtils, JournalService journalService,
                          ModelMapper modelMapper) {
    this.courseService = courseService;
    this.accountService = accountService;
    this.topicService = topicService;
    this.courseValidator = courseValidator;
    this.securityUtils = securityUtils;
    this.journalService = journalService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/{id}")
  public String course(@PathVariable("id") int id, Model model) {
    Optional<Course> optCourse = courseService.findById(id);
    if (optCourse.isEmpty()) return "courses/all";
    model.addAttribute(COURSE_ATTR, optCourse.get());
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.ROLE_TEACHER));
    return COURSE_PAGE;
  }

  @GetMapping("/all")
  public String allCourses(
      @RequestParam(value = SORT_PARAM, required = false) String sort,
      @RequestParam(value = TEACHER_PARAM, required = false) String teacher,
      @RequestParam(value = TOPIC_PARAM, required = false) String topic, Model model) {
    CourseSelection selection = new CourseSelection(sort, teacher, topic);
    model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.ROLE_TEACHER));
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    List<Course> courses = courseService.getAll(selection);
    model.addAttribute(COURSES_ATTR, convertToCourseDTO(courses));
    return ALL_COURSES_PAGE;
  }

  @GetMapping("/available")
  public String availableCourses(
      @RequestParam(value = SORT_PARAM, required = false) String sort,
      @RequestParam(value = TEACHER_PARAM, required = false) String teacher,
      @RequestParam(value = TOPIC_PARAM, required = false) String topic, Model model) {
    int studentId = securityUtils.getUserId();
    CourseSelection selection = new CourseSelection(sort, teacher, topic);
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.ROLE_TEACHER));
    List<Course> courses = courseService.getAvailable(studentId, selection);
    model.addAttribute(AVAILABLE_COURSES_ATTR, convertToCourseDTO(courses));
    return AVAILABLE_COURSES_PAGE;
  }

  @GetMapping("/registered")
  public String registeredCourses(Model model) {
    int studentId = securityUtils.getUserId();
    List<Course> courses = courseService.getRegisteredCourses(studentId);
    model.addAttribute(REGISTERED_COURSES_ATTR,
        convertToRegisteredCourseDTO(courses, studentId));
    return REGISTERED_COURSES_PAGE;
  }

  private List<RegisteredCourseDTO> convertToRegisteredCourseDTO(
      List<Course> courses, int studentId) {
    return courses.stream().map(course -> {
      Journal studentCourse = course
          .getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get();
      Date registrationDate = studentCourse.getEnrollmentDate();
      CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
      return new RegisteredCourseDTO(courseDTO, registrationDate);
    }).toList();
  }

  @GetMapping("/ongoing")
  public String ongoingCourses(Model model) {
    int studentId = securityUtils.getUserId();
    List<Course> courses = courseService.getOngoingCourses(studentId);
    model.addAttribute(ONGOING_COURSES_ATTR, convertToCourseDTO(courses));
    return ONGOING_COURSES_PAGE;
  }

  private List<CourseDTO> convertToCourseDTO(List<Course> courses) {
    return courses
        .stream()
        .map(course -> modelMapper.map(course, CourseDTO.class))
        .toList();
  }

  @GetMapping("/completed")
  public String completedCourses(Model model) {
    int studentId = securityUtils.getUserId();
    List<Course> courses = courseService.getCompletedCourses(studentId);
    model.addAttribute(COMPLETED_COURSES_ATTR,
        convertToCompletedCourseDTO(courses, studentId));
    return COMPLETED_COURSES_PAGE;
  }

  private List<CompletedCourseDTO> convertToCompletedCourseDTO(
      List<Course> courses, int studentId) {
    return courses.stream().map(course -> {
      int grade = course.getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get()
          .getGrade();
      CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
      return new CompletedCourseDTO(courseDTO, grade);
    }).toList();
  }

  @GetMapping("/add")
  public String addCourseForm(Model model) {
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.ROLE_TEACHER));
    model.addAttribute(TOPIC_PARAM, new Topic());
    model.addAttribute(TEACHER_PARAM, new Account());
    model.addAttribute(COURSE_ATTR, new Course());
    return ADD_COURSE_PAGE;
  }

  @PostMapping("/edit")
  public String editCourse(@RequestParam(TEACHER_ID_PARAM) int teacherId,
                           @RequestParam(TOPIC_ID_PARAM) int topicId,
                           @ModelAttribute(COURSE_ATTR) @Valid Course course,
                           BindingResult result, Model model) {
    courseValidator.validate(course, result);
    if (result.hasErrors()) {
      model.addAttribute(TOPICS_ATTR, topicService.getAll());
      model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.ROLE_TEACHER));
      course.setTopic(topicService.get(topicId));
      course.setTeacher(accountService.get(teacherId));
      return COURSE_PAGE;
    }
    courseService.persist(course, teacherId, topicId);
    return "redirect:all";
  }

  @PostMapping("/delete/{id}")
  public String deleteCourse(@PathVariable("id") int id) {
    courseService.delete(id);
    return "redirect:../all";
  }


  @PostMapping("/add")
  public String addCourse(@RequestParam(TOPIC_ID_PARAM) int topicId,
                          @RequestParam(TEACHER_ID_PARAM) int teacherId,
                          @ModelAttribute(COURSE_ATTR) @Valid Course course,
                          BindingResult result, Model model) {
    courseValidator.validate(course, result);
    if (result.hasErrors()) {
      model.addAttribute(TOPICS_ATTR, topicService.getAll());
      model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.ROLE_TEACHER));
      return ADD_COURSE_PAGE;
    }
    courseService.persist(course, teacherId, topicId);
    return "redirect:all";
  }

  @PostMapping("/enroll/{id}")
  public String enroll(@PathVariable("id") int courseId) {
    int id = securityUtils.getUserId();
    journalService.save(courseId, id);
    return "redirect:../available";
  }

}
