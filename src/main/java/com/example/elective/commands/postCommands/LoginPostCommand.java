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

/**
 * Class with method that calls corresponding service method when user submits login form
 * @author Kirill Biliashov
 */

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
    Optional<Account> optAccount = null;
    try {
      optAccount = service.findByCredentials(login, password);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
    handleOptionalAccount(optAccount);
  }

  private void handleOptionalAccount(Optional<Account> optAccount)
      throws ServletException, IOException {
    Account acc = optAccount.orElse(null);
    if (!optAccount.isPresent()) {
      handleAbsentAccount();
    } else if (acc.isBlocked()) {
      handleBlockedAccount();
    } else {
      addAccountToSession(acc);
      resp.sendRedirect(REDIRECT_URL);
    }
  }

  private void handleAbsentAccount() throws ServletException, IOException {
    req.setAttribute("loginFailed", true);
    forward(JSP_PAGE);
  }

  private void handleBlockedAccount() throws ServletException, IOException {
    req.setAttribute("accountBlocked", true);
    forward(JSP_PAGE);
  }

  private void addAccountToSession(Account acc) {
    HttpSession session = req.getSession();
    session.setAttribute(ACCOUNT_ATTR, acc);
  }

}
