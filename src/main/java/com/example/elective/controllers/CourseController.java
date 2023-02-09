package com.example.elective.controllers;

import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.models.*;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.CourseService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TopicService;
import com.example.elective.utils.CourseSelection;
import com.example.elective.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.Constants.TEACHERS_ATTR;

@Controller
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private CourseService courseService;
  @Autowired
  private AccountService accountService;
  @Autowired
  private TopicService topicService;
  @Autowired
  private HttpUtils utils;
  @Autowired
  private AccountService accService;
  @Autowired
  private CourseDTOMapper dtoMapper;
  @Autowired
  private JournalService journalService;

  @GetMapping("/{id}")
  public String course(@PathVariable("id") int id, Model model) {
    Optional<Course> optCourse = courseService.findById(id);
    if (optCourse.isEmpty()) return "courses/all";
    model.addAttribute(COURSE_ATTR, optCourse.get());
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.TEACHER));
    return "courses/course";
  }

  @GetMapping("/all")
  public String allCourses(Model model,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(value = "teacher", required = false) String teacher,
                           @RequestParam(value = "topic", required = false) String topic) {
    CourseSelection selection = new CourseSelection(sort, teacher, topic);
    model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.TEACHER));
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(SORT_TYPES_ATTR, SORT_TYPES);
    List<Course> courses = courseService.getAll(selection);
    model.addAttribute(COURSES_ATTR,
        courses.stream().map(dtoMapper::map).toList());
    return "courses/all";
  }

  @GetMapping("/available")
  public String availableCourses(Model model, HttpServletRequest req,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "teacher", required = false) String teacher,
                                 @RequestParam(value = "topic", required = false) String topic) {
    int studentId = utils.getCurrentUserId(req);
    CourseSelection selection = new CourseSelection(sort, teacher, topic);
    model.addAttribute(SORT_TYPES_ATTR, SORT_TYPES);
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(TEACHERS_ATTR, accService.getAll(Role.TEACHER));
    List<Course> courses = courseService.getAvailable(studentId, selection);
    model.addAttribute(AVAILABLE_COURSES_ATTR, courses.stream().map(dtoMapper::map).toList());
    return "courses/available";
  }

  @GetMapping("/registered")
  public String registeredCourses(Model model, HttpServletRequest req) {
    int studentId = utils.getCurrentUserId(req);
    List<Course> courses = courseService.getRegisteredCourses(studentId);
    model.addAttribute(REGISTERED_COURSES_ATTR, getDTOList(courses, studentId));
    return "courses/registered";
  }

  private List<RegisteredCourseDTO> getDTOList(List<Course> courses, int studentId) {
    return courses.stream().map(course -> {
      Journal studentCourse = course
          .getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get();
      Date registrationDate = studentCourse.getEnrollmentDate();
      return new RegisteredCourseDTO(dtoMapper.map(course), registrationDate);
    }).toList();
  }

  @GetMapping("/ongoing")
  public String ongoingCourses(Model model, HttpServletRequest req) {
    int studentId = utils.getCurrentUserId(req);
    List<Course> courses = courseService.getCoursesInProgress(studentId);
    model.addAttribute(COURSES_IN_PROGRESS_ATTR, courses
        .stream()
        .map(dtoMapper::map)
        .toList());
    return "courses/ongoing";
  }

  @GetMapping("/completed")
  public String completedCourses(Model model, HttpServletRequest req) {
    int studentId = utils.getCurrentUserId(req);
    List<Course> courses = courseService.getCompletedCourses(studentId);
    model.addAttribute(COMPLETED_COURSES_ATTR,
        getCompletedCourseDTOList(courses, studentId));
    return "courses/completed";
  }

  private List<CompletedCourseDTO> getCompletedCourseDTOList(
      List<Course> courses, int studentId) {
    return courses.stream().map(course -> {
      int grade = course
          .getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get()
          .getGrade();
      return new CompletedCourseDTO(dtoMapper.map(course), grade);
    }).toList();
  }

  @GetMapping("/add")
  public String addCourseForm(Model model) {
   model.addAttribute(TOPICS_ATTR, topicService.getAll());
   model.addAttribute(TEACHERS_ATTR, accountService.getAll(Role.TEACHER));
   model.addAttribute("topic", new Topic());
   model.addAttribute("teacher", new Account());
   model.addAttribute(COURSE_ATTR, new Course());
    return "courses/add";
  }

  @PostMapping("/edit")
  public String editCourse(@ModelAttribute(COURSE_ATTR) Course course,
                           @RequestParam("teacherId") int teacherId,
                           @RequestParam("topicId") int topicId) {
    courseService.persist(course, teacherId, topicId);
    return "redirect:../all";
  }

  @PostMapping("/delete/{id}")
  public String deleteCourse(@PathVariable("id") int id) {
    courseService.delete(id);
    return "redirect:../all";
  }


  @PostMapping("/add")
  public String addCourse(@ModelAttribute(COURSE_ATTR) Course course,
                          @RequestParam("topicId") int topicId,
                          @RequestParam("teacherId") int teacherId) {
    courseService.persist(course, teacherId, topicId);
    return "redirect:../all";
  }

  @PostMapping("/enroll/{id}")
  public String enroll(@PathVariable("id") int courseId, HttpServletRequest req) {
    journalService.save(courseId, utils.getCurrentUserId(req));
    return "redirect:../available";
  }

}
