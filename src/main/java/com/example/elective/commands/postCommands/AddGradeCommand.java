package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.interfaces.JournalService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

/**
 * Class that adds student's grade for the course
 * @author Kirill Biliashov
 */

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
    service.updateGrade(journalId, Integer.parseInt(gradeStr));
    resp.sendRedirect(getRedirectUrl());
  }

  private String getRedirectUrl() {
    return "/elective/teacher?page=" + req.getParameter(PAGE_ATTR) +
        "&display=" + req.getParameter(DISPLAY_PARAM);
  }

}
