package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.AccountService;
import com.example.elective.services.TeacherService;
import com.example.elective.utils.RegexUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/teachers")
public class TeachersServlet extends HttpServlet {

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
      int page = getPageNumber(req);
      setPageAttributes(req, page);
      int pagesCount = accountService.getPagesCount("Teacher");
      req.setAttribute("pagesCount", pagesCount);
      if (page <= pagesCount) {
        req.setAttribute("teachers", accountService.getAtPage(page));
      }
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/admin-teachers.jsp").forward(req, resp);
  }

  private void setPageAttributes(HttpServletRequest req, int currPage) {
    int nextPage = currPage + 1;
    int prevPage = currPage - 1;
    req.setAttribute("page", currPage);
    req.setAttribute("next", nextPage);
    req.setAttribute("prev", prevPage);
  }

  private int getPageNumber(HttpServletRequest request) {
    String pageParam = request.getParameter("page");
    if (!RegexUtils.isNumeric(pageParam)) return 1;
    return Integer.parseInt(pageParam);
  }

}
