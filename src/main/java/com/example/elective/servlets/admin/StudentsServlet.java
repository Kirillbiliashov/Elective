package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AccountService;
import com.example.elective.utils.PaginationUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.PaginationUtils.getItemsPerPage;
import static com.example.elective.utils.PaginationUtils.getPageNumber;

@WebServlet("/admin/students")
public class StudentsServlet extends HttpServlet {

  private static final String JSP_PAGE = "/admin-students.jsp";
  private AccountService accountService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    accountService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int page = getPageNumber(req);
    int displayCount = getItemsPerPage(req);
    try {
      int total = accountService.getTotalCount(STUDENT_ROLE);
      Pagination pagination = new Pagination(page, displayCount, total);
      PaginationUtils.setPageAttributes(req, page);
      req.setAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
      if (page <= pagination.getPagesCount()) req.setAttribute(STUDENTS_ATTR,
            accountService.getPaginated(STUDENT_ROLE, pagination));
      req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
