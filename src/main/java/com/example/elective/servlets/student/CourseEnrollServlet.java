package com.example.elective.servlets.student;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.JournalRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Journal;
import com.example.elective.services.JournalService;
import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.JOURNAL_SERVICE;

/**
 * Servlet class that handles POST request for url mapping "/student/courses/enroll/*"
 * @author Kirill Biliashov
 */

@WebServlet("/student/courses/enroll/*")
public class CourseEnrollServlet extends HttpServlet {

  private static final String REDIRECT_URL = "/elective/student";
  private final RequestMapper<Journal> journalMapper = new JournalRequestMapper();
  private JournalService journalService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    journalService = (JournalService) context.getAttribute(JOURNAL_SERVICE);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Journal journal = journalMapper.map(req);
    try {
      journalService.save(journal);
      resp.sendRedirect(REDIRECT_URL);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
