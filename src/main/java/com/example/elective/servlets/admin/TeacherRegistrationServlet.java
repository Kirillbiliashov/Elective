package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.AccountRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;
import static com.example.elective.utils.Constants.LOGINS_ATTR;

/**
 * Servlet class that handles GET and POST requests for mapping "/admin/teachers/register"
 * @author Kirill Biliashov
 */

@WebServlet("/admin/teachers/register")
public class TeacherRegistrationServlet extends HttpServlet {

  private static final String JSP_PAGE = "/teacher-register-form.jsp";
  protected static final String REDIRECT_URL = "/elective/admin/teachers";
  private final RequestMapper<Account> accountMapper = new AccountRequestMapper();
  private AccountService accService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    accService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      req.setAttribute(LOGINS_ATTR, accService.getLogins());
      req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Account acc = accountMapper.map(req);
    try {
      accService.save(acc);
      resp.sendRedirect(REDIRECT_URL);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
