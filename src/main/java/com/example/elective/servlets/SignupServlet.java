package com.example.elective.servlets;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.AccountRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;
import com.example.elective.utils.Constants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

  private static final String JSP_PAGE = "signup-form.jsp";
  private final RequestMapper<Account> accountMapper = new AccountRequestMapper();
  private AccountService accService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    accService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Account acc = accountMapper.map(req);
    try {
      accService.save(acc);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(Constants.LOGIN_URL);
  }

}
