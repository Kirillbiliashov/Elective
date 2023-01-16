package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

/**
 * Class that forms and redirects logged-in user to his home url
 * @author Kirill Biliashov
 */

public class MainCommand extends Command {

  @Override
  public void process() throws IOException {
    HttpSession session = req.getSession();
    Account account = (Account) session.getAttribute(ACCOUNT_ATTR);
    String homeUrl = LOGIN_URL;
    if (account != null) homeUrl = account.getRole().toLowerCase();
    session.setAttribute(HOME_URL_ATTR, homeUrl);
    resp.sendRedirect(homeUrl);
  }

}
