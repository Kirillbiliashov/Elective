package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.selection.Pagination;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.utils.PaginationUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

public class TeachersCommand extends Command {
  private AccountService service;
  private static final String JSP_PAGE = "/admin-teachers.jsp";

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int page = PaginationUtils.getPageNumber(req);
    int displayCount = PaginationUtils.getItemsPerPage(req);
    try {
      int total = service.getTotalCount(TEACHER_ROLE);
      Pagination pagination = new Pagination(page, displayCount, total);
      PaginationUtils.setPageAttributes(req, pagination.getPage());
      req.setAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
      req.setAttribute(TEACHERS_ATTR,
          service.getPaginated(TEACHER_ROLE, pagination));
      forward(JSP_PAGE);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
