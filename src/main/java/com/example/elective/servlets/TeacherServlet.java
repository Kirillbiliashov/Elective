package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    List<Course> courses = CourseDAO.getByTeacherId(teacherId);
    for (final Course course: courses) {
      map.put(course, getJournalStudentMap(course.getId()));
    }
    return map;
  }

  private Map<Journal, Account> getJournalStudentMap(int courseId) {
   Map<Journal, Account> map = new LinkedHashMap<>();
    List<Journal> journalList = JournalDAO.getByCourseId(courseId);
    for (final Journal journal: journalList) {
      Account student = AccountDAO.getById(journal.getStudentId()).orElse(null);
      map.put(journal, student);
    }
    return map;
  }

}