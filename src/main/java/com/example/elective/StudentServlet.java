package com.example.elective;

import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;

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

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    int studentId = ((Account) session.getAttribute("account")).getId();
    Map<Course, Journal> courseJournalMap = getCourseJournalMap(CourseDAO.getAll(), studentId);
    req.setAttribute("courseJournalMap", courseJournalMap);
    req.getRequestDispatcher("student.jsp").forward(req, resp);
  }

  private Map<Course, Journal> getCourseJournalMap(List<Course> courses, int studentId) {
    Map<Course, Journal> map = new LinkedHashMap<>();
    for (final Course course: courses) {
      map.put(course, JournalDAO.findByCourseAndStudent(course.getId(), studentId).orElse(null));
    }
    return map;
  }

}
