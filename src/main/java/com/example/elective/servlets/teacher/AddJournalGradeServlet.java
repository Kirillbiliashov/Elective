package com.example.elective.servlets.teacher;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.JournalService;
import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.RequestUtils.getIdFromPathInfo;

@WebServlet("/teacher/addGrade/*")
public class AddJournalGradeServlet extends HttpServlet {

  private static final String GRADE_PARAM = "grade";
  private JournalService journalService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    journalService = (JournalService) context.getAttribute(JOURNAL_SERVICE);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int journalId = getIdFromPathInfo(req.getPathInfo());
    String gradeStr = req.getParameter(GRADE_PARAM);
    try {
      journalService.updateGrade(journalId, Integer.parseInt(gradeStr));
      resp.sendRedirect(getRedirectUrl(req));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private String getRedirectUrl(HttpServletRequest req) {
    return "/elective/teacher?page=" + req.getParameter(PAGE_ATTR) +
        "&display=" + req.getParameter(DISPLAY_PARAM);
  }

}
