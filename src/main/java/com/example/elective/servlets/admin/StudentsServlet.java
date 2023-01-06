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
    try {
      int page = PaginationUtils.getPageNumber(req);
      int displayCount = PaginationUtils.getItemsPerPage(req);
      PaginationUtils.setPageAttributes(req, page);
      int pagesCount = (int) Math.ceil(accountService.getTotalCount(STUDENT_ROLE)
          / (double) displayCount);
      req.setAttribute(PAGES_COUNT_ATTR, pagesCount);
      if (page <= pagesCount) {
        req.setAttribute(STUDENTS_ATTR, accountService.getPaginated(STUDENT_ROLE,
            new Pagination(page, displayCount)));
      }
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
  }

}
