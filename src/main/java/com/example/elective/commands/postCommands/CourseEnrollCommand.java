package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;
import com.example.elective.services.interfaces.JournalService;
import org.hibernate.Session;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;
import static com.example.elective.utils.Constants.JOURNAL_SERVICE;

/**
 * Class with method that calls corresponding service method when client enrolls in the course
 * @author Kirill Biliashov
 */

public class CourseEnrollCommand extends Command {

  private static final String REDIRECT_URL = "/elective/student";
  private JournalService journalService;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (journalService == null) journalService =
        (JournalService) context.getAttribute(JOURNAL_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int courseId = Integer.parseInt(req.getParameter("courseId"));
    journalService.save(courseId, getCurrentUserId());
    resp.sendRedirect(REDIRECT_URL);
  }


}
