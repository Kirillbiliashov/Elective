package com.example.elective.controllers;

import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.mappers.requestMappers.CourseSelectionRequestMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.models.Topic;
import com.example.elective.selection.CourseSelection;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.CourseService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TopicService;
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
  private CourseSelectionRequestMapper selectionMapper;
  @Autowired
  private AccountService accService;
  @Autowired
  private CourseDTOMapper dtoMapper;
  @Autowired
  private JournalService journalService;

  @GetMapping("/{id}")
  public String course(@PathVariable("id") int id, Model model) {
    Optional<Course> optCourse = courseService.findById(id);
    if (optCourse.isEmpty()) return "allCourses";
    model.addAttribute(COURSE_ATTR, optCourse.get());
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(TEACHERS_ATTR, accountService.getTeachers());
    return "course";
  }

  @GetMapping("/all")
  public String allCourses(HttpServletRequest req, Model model) {
    CourseSelection selection = selectionMapper.map(req);
    List<Course> courses = courseService.getAll();
    model.addAttribute(TEACHERS_ATTR, accountService.getTeachers());
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(SORT_TYPES_ATTR, SORT_TYPES);
    model.addAttribute(COURSES_ATTR,
        selection.getSelected(courses.stream().map(dtoMapper::map).toList()));
    return "allCourses";
  }

  @GetMapping("/available")
  public String availableCourses(HttpServletRequest req, Model model) {
    int studentId = utils.getCurrentUserId(req);
    CourseSelection selection = selectionMapper.map(req);
    model.addAttribute(SORT_TYPES_ATTR, SORT_TYPES);
    model.addAttribute(TOPICS_ATTR, topicService.getAll());
    model.addAttribute(TEACHERS_ATTR, accService.getTeachers());
    List<Course> courses = courseService.getAvailable(studentId);
    model.addAttribute(AVAILABLE_COURSES_ATTR,
        selection.getSelected(courses.stream().map(dtoMapper::map).toList()));
    return "availableCourses";
  }

  @GetMapping("/registered")
  public String registeredCourses(Model model, HttpServletRequest req) {
    int studentId = utils.getCurrentUserId(req);
    List<Course> courses = courseService.getRegisteredCourses(studentId);
    model.addAttribute(REGISTERED_COURSES_ATTR, getDTOList(courses, studentId));
    return "registeredCourses";
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
    return "ongoingCourses";
  }

  @GetMapping("/completed")
  public String completedCourses(Model model, HttpServletRequest req) {
    int studentId = utils.getCurrentUserId(req);
    List<Course> courses = courseService.getCompletedCourses(studentId);
    model.addAttribute(COMPLETED_COURSES_ATTR,
        getCompletedCourseDTOList(courses, studentId));
    return "completedCourses";
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
   model.addAttribute(TEACHERS_ATTR, accountService.getTeachers());
   model.addAttribute("topic", new Topic());
   model.addAttribute("teacher", new Account());
   model.addAttribute(COURSE_ATTR, new Course());
    return "addCourse";
  }

  @PostMapping("/edit")
  public String editCourse(@ModelAttribute(COURSE_ATTR) Course course,
                           @RequestParam("teacherId") int teacherId,
                           @RequestParam("topicId") int topicId) {
    courseService.update(course, teacherId, topicId);
    return "redirect:all";
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
    courseService.save(course, topicId, teacherId);
    return "redirect:all";
  }

  @PostMapping("/enroll/{id}")
  public String enroll(@PathVariable("id") int courseId, HttpServletRequest req) {
    journalService.save(courseId, utils.getCurrentUserId(req));
    return "redirect:../available";
  }

}
