package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AccountService;
import com.example.elective.utils.PaginationUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.elective.utils.Constants.*;

public abstract class PaginationHttpServlet extends HttpServlet {

  protected AccountService accountService;
  protected String role;
  protected String attrName;
  protected String jspPage;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    accountService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
    setFields();
  }

  abstract void setFields();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int page = PaginationUtils.getPageNumber(req);
    int displayCount = PaginationUtils.getItemsPerPage(req);
    try {
      int total = accountService.getTotalCount(role);
      Pagination pagination = new Pagination(page, displayCount, total);
      PaginationUtils.setPageAttributes(req, pagination.getPage());
      req.setAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
       req.setAttribute(attrName, accountService.getPaginated(role, pagination));
      req.getRequestDispatcher(jspPage).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
