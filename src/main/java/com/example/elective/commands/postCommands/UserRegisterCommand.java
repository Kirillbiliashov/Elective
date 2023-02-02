package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.AccountRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Account;
import com.example.elective.services.interfaces.AccountService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;

/**
 * Class with method that calls corresponding service method when
 * teacher/student registration form is submitted
 * @author Kirill Biliashov
 */

public class UserRegisterCommand extends Command {

  private final String redirectUrl;
  private final RequestMapper<Account> accountMapper = new AccountRequestMapper();
  private AccountService service;

  public UserRegisterCommand(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    Account acc = accountMapper.map(req);
    service.save(acc);
    resp.sendRedirect(redirectUrl);
  }

}
