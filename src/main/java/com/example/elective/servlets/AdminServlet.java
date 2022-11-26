package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.dao.TopicDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Topic;
import com.example.elective.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(value = "/admin", name = "admin")
public class AdminServlet extends HttpServlet {

  private CourseService courseService = new CourseService();
  private TeacherService teacherService = new TeacherService();
  private TopicService topicService = new TopicService();
  private JournalService journalService = new JournalService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Course> courses = courseService.getAll();
    String teacherIdStr = req.getParameter("teacher");
    if (Utils.isNumeric(teacherIdStr)) {
      int teacherId = Integer.parseInt(teacherIdStr);
      courses = filterByTeacherId(courses, teacherId);
    }
    String topicIdStr = req.getParameter("topic");
    if (Utils.isNumeric(topicIdStr)) {
      int topicId = Integer.parseInt(topicIdStr);
      courses = filterByTopicId(courses, topicId);
    }
    String sortType = req.getParameter("sort");
    if (sortType != null) sortCourses(sortType, courses);
    req.setAttribute("topics", topicService.getAll());
    req.setAttribute("courses", courseService.getCourseTeacher(courses));
    req.setAttribute("teachers", teacherService.getAll());
    req.getRequestDispatcher("/admin.jsp").forward(req, resp);
  }

  private List<Course> filterByTeacherId(List<Course> courses, int teacherId) {
    return courses
        .stream()
        .filter(course -> course.getTeacherId() == teacherId)
        .collect(Collectors.toList());
  }

  private List<Course> filterByTopicId(List<Course> courses, int topicId) {
    return courses
        .stream()
        .filter(course -> course.getTopicId() == topicId)
        .collect(Collectors.toList());
  }

  private void sortCourses(String sortType, List<Course> courses) {
    Comparator<Course> courseComparator = getCourseComparator(sortType);
    if (courseComparator != null) courses.sort(courseComparator);
  }

  private Comparator<Course> getCourseComparator(String sortType) {
    Comparator<Course> courseComparator = null;
    if (sortType.equals("name")) {
      courseComparator = getNameComparator();
    } else if (sortType.equals("name_reverse")) {
      courseComparator = getNameComparator().reversed();
    } else if (sortType.equals("duration_asc")) {
      courseComparator = getDurationComparator();
    } else if (sortType.equals("duration_desc")) {
      courseComparator = Collections.reverseOrder(getDurationComparator());
    } else if (sortType.equals("students_asc")) {
      courseComparator = getStudentComparator();
    } else if (sortType.equals("students_desc")) {
      courseComparator = Collections.reverseOrder(getStudentComparator());
    }
    return courseComparator;
  }

  private Comparator<Course> getNameComparator() {
    return Comparator.comparing(Course::getName);
  }

  private Comparator<Course> getDurationComparator() {
  return Comparator.comparing(c -> c.getEndDate().getTime() -
      c.getStartDate().getTime());
  }

  private Comparator<Course> getStudentComparator() {
    return Comparator.comparing(c -> journalService.getByCourseIdCount(c.getId()));
  }

}
