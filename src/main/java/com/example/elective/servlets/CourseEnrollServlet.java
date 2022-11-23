package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
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

@WebServlet("/courses/enroll/*")
public class CourseEnrollServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Journal journal = mapRequestToJournal(req);
    JournalDAO.save(journal);
    resp.sendRedirect("student");
  }

  private Journal mapRequestToJournal(HttpServletRequest req) {
    int courseId = Utils.getIdFromPathInfo(req.getPathInfo());
    HttpSession session = req.getSession();
    int studentId = ((Account) session.getAttribute("account")).getId();
    return new Journal()
        .setCourseId(courseId)
        .setStudentId(studentId)
        .setEnrollmentDate(Utils.CURRENT_DATE);
  }

}
