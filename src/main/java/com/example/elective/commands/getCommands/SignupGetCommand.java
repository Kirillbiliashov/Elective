package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.interfaces.AccountService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;
import static com.example.elective.utils.Constants.LOGINS_ATTR;

/**
 * Class that renders signup JSP page
 * @author Kirill Biliashov
 */

public class SignupGetCommand extends Command {

  private static final String JSP_PAGE = "signup-form.jsp";
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
    req.setAttribute(LOGINS_ATTR, service.getLogins());
    forward(JSP_PAGE);
  }

}
