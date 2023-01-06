package com.example.elective.servlets;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;
import com.example.elective.utils.PasswordUtils;
import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;
import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private static final String JSP_PAGE = "login-form.jsp";
  private static final String REDIRECT_URL = "main";
  private static final String LOGIN_PARAM = "login";
  private static final String PASSWORD_PARAM = "password";
  private AccountService accService;

  @Override
  public void init(ServletConfig config) {
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
      throws ServletException, IOException {
    String login = req.getParameter(LOGIN_PARAM);
    String password = req.getParameter(PASSWORD_PARAM);
    try {
      Optional<Account> optAccount = accService.findByCredentials(login, password);
      if (!optAccount.isPresent()) {
        handleAbsentAccount(req, resp);
        return;
      }
      Account acc = optAccount.get();
      if (acc.isBlocked()) {
        handleBlockedAccount(req, resp);
        return;
      }
      addAccountToSession(req, acc);
      resp.sendRedirect(REDIRECT_URL);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void handleAbsentAccount(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("loginFailed", true);
    req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
  }

  private void handleBlockedAccount(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("accountBlocked", true);
    req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
  }

  private void addAccountToSession(HttpServletRequest req, Account acc) {
    HttpSession session = req.getSession();
    session.setAttribute(ACCOUNT_ATTR, acc);
  }

}
