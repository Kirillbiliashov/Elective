package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.JournalRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Journal;
import com.example.elective.services.JournalService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.JOURNAL_SERVICE;

public class CourseEnrollCommand extends Command {

  private static final String REDIRECT_URL = "/elective/student";
  private final RequestMapper<Journal> journalMapper = new JournalRequestMapper();
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
    Journal journal = journalMapper.map(req);
    try {
      journalService.save(journal);
      resp.sendRedirect(REDIRECT_URL);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
