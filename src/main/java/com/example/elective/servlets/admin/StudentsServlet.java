package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.AccountService;
import com.example.elective.services.StudentService;
import com.example.elective.utils.PaginationUtils;
import com.example.elective.utils.RegexUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/students")
public class StudentsServlet extends HttpServlet {

  private AccountService accountService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    accountService = (AccountService) context.getAttribute("accountService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      int page = PaginationUtils.getPageNumber(req);
      PaginationUtils.setPageAttributes(req, page);
      int pagesCount = accountService.getPagesCount("Student");
      req.setAttribute("pagesCount", pagesCount);
      if (page <= pagesCount) {
        req.setAttribute("students", accountService.getAtPage("Student", page));
      }
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/admin-students.jsp").forward(req, resp);
  }

}
