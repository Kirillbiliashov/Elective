package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.interfaces.AccountService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;
import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;

public class LoginPostCommand extends Command {

  protected static final String JSP_PAGE = "login-form.jsp";
  protected static final String REDIRECT_URL = "main";
  protected static final String LOGIN_PARAM = "login";
  protected static final String PASSWORD_PARAM = "password";
  private AccountService service;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    String login = req.getParameter(LOGIN_PARAM);
    String password = req.getParameter(PASSWORD_PARAM);
    try {
      Optional<Account> optAccount = service.findByCredentials(login, password);
      Account acc = optAccount.orElse(null);
      if (!optAccount.isPresent()) {
        handleAbsentAccount(req, resp);
      } else if (acc.isBlocked()) {
        handleBlockedAccount(req, resp);
      } else {

        addAccountToSession(req, acc);
        resp.sendRedirect(REDIRECT_URL);
      }
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
