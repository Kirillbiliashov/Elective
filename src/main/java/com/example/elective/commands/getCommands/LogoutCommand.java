package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

public class LogoutCommand extends Command {

  @Override
  public void process() throws IOException {
    HttpSession session = req.getSession();
    session.setAttribute(ACCOUNT_ATTR, null);
    session.setAttribute(HOME_URL_ATTR, LOGIN_URL);
    resp.sendRedirect(LOGIN_URL);
  }

}

