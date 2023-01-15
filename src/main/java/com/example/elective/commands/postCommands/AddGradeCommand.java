package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.JournalService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

public class AddGradeCommand extends Command {

  private static final String GRADE_PARAM = "grade";
  private JournalService service;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (JournalService) context.getAttribute(JOURNAL_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int journalId = getIdFromPathInfo();
    String gradeStr = req.getParameter(GRADE_PARAM);
    try {
      service.updateGrade(journalId, Integer.parseInt(gradeStr));
      resp.sendRedirect(getRedirectUrl());
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

    private String getRedirectUrl() {
    return "/elective/teacher?page=" + req.getParameter(PAGE_ATTR) +
        "&display=" + req.getParameter(DISPLAY_PARAM);
  }

}
