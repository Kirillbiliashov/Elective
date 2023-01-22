package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.BlocklistService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;

/**
 * Class with method that calls corresponding service method when user submits login form
 * @author Kirill Biliashov
 */

public class LoginPostCommand extends Command {

  protected static final String JSP_PAGE = "login-form.jsp";
  protected static final String REDIRECT_URL = "main";
  protected static final String LOGIN_PARAM = "login";
  protected static final String PASSWORD_PARAM = "password";
  private AccountService accountService;
  private BlocklistService blocklistService;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (accountService == null) accountService =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
    if (blocklistService == null) blocklistService =
        (BlocklistService) context.getAttribute(BLOCKLIST_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    String login = req.getParameter(LOGIN_PARAM);
    String password = req.getParameter(PASSWORD_PARAM);
    Optional<Account> optAccount;
    try {
      optAccount = accountService.findByCredentials(login, password);
      handleOptionalAccount(optAccount);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
  }

  private void handleOptionalAccount(Optional<Account> optAccount)
      throws ServletException, IOException, ServiceException {
    Account acc = optAccount.orElse(null);
    if (!optAccount.isPresent()) {
      handleAbsentAccount();
    } else if (blocklistService.getBlockStatus(acc.getId()).isPresent()) {
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
