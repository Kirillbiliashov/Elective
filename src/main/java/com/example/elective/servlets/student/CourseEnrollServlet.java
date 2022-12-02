package com.example.elective.servlets.student;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.requestMappers.JournalRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Journal;
import com.example.elective.services.JournalService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/courses/enroll/*")
public class CourseEnrollServlet extends HttpServlet {

  private RequestMapper<Journal> journalMapper = new JournalRequestMapper();
  private JournalService journalService = new JournalService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Journal journal = journalMapper.map(req);
    try {
      journalService.save(journal);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect("/elective/student");
  }

}
