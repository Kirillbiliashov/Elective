package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.services.CourseService;
import com.example.elective.services.JournalService;

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

@WebServlet(value = "/teacher", name = "teacher")
public class TeacherServlet extends HttpServlet {

  private JournalService journalService = new JournalService();
  private CourseService courseService = new CourseService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    Account teacherAcc = (Account) session.getAttribute("account");
    int id = teacherAcc.getId();
    Map<Course, Map<Journal, Account>> journal = getJournal(id);
    req.setAttribute("journal", journal);
    req.setAttribute("currDate", Utils.CURRENT_DATE);
    req.getRequestDispatcher("teacher.jsp").forward(req, resp);
  }

  private Map<Course, Map<Journal, Account>> getJournal(int teacherId) {
    Map<Course, Map<Journal, Account>> map = new LinkedHashMap<>();
    List<Course> courses = courseService.getByTeacherId(teacherId);
    for (final Course course: courses) {
      map.put(course, journalService.getJournalStudent(course.getId()));
    }
    return map;
  }

}