package com.example.elective.servlets.teacher;

import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.JournalService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/addGrade/*")
public class AddJournalGradeServlet extends HttpServlet {

  private JournalService journalService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    journalService = (JournalService) context.getAttribute("journalService");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int journalId = Utils.getIdFromPathInfo(req.getPathInfo());
    String gradeStr = req.getParameter("grade");
    try {
      journalService.updateGradeById(journalId, Integer.parseInt(gradeStr));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect("/elective/teacher?page=1");
  }

}
