package com.example.elective.servlets.student;

import com.example.elective.Utils;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.services.CourseService;
import com.example.elective.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

  private StudentService studentService = new StudentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int studentId = Utils.getCurrentUserId(req);
    Map<Course, Journal> courseJournal = null;
    try {
      courseJournal = studentService.getCourseJournal(studentId);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.setAttribute("unregisteredCourses", getUnregisteredCourses(courseJournal));
    req.setAttribute("coursesInProgress", getCoursesInProgressMap(courseJournal));
    req.setAttribute("completedCourses", getCompletedCoursesMap(courseJournal));
    req.setAttribute("registeredCourses", getRegisteredCoursesMap(courseJournal));
    req.getRequestDispatcher("student.jsp").forward(req, resp);
  }

  private List<Course> getUnregisteredCourses(Map<Course, Journal> courseJournalMap) {
    return courseJournalMap
        .entrySet()
        .stream()
        .filter(e -> e.getValue() == null)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  private Map<Course, Journal> getRegisteredCoursesMap(Map<Course, Journal> courseJournalMap) {
    return courseJournalMap
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() != null)
        .filter(entry -> entry.getKey().getStartDate().after(Utils.CURRENT_DATE))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private Map<Course, Journal> getCoursesInProgressMap(Map<Course, Journal> courseJournalMap) {
    return courseJournalMap
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() != null)
        .filter(entry -> entry.getKey().getStartDate().before(Utils.CURRENT_DATE))
        .filter(entry -> entry.getKey().getEndDate().after(Utils.CURRENT_DATE))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private Map<Course, Journal> getCompletedCoursesMap(Map<Course, Journal> courseJournalMap) {
    return courseJournalMap
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() != null)
        .filter(entry -> entry.getValue().getGrade() != -1)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

}