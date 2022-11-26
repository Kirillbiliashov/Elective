package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.JournalDAO;
import com.example.elective.mappers.JournalRequestMapper;
import com.example.elective.mappers.RequestMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;
import com.example.elective.services.JournalService;

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

  private RequestMapper<Journal> journalMapper = new JournalRequestMapper();
  private JournalService journalService = new JournalService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Journal journal = journalMapper.map(req);
    journalService.save(journal);
    resp.sendRedirect("/elective/student");
  }

}
