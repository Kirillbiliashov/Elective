package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Class that renders login page
 * @author Kirill Biliashov
 */

public class LoginGetCommand extends Command {

  private static final String JSP_PAGE = "login-form.jsp";

  @Override
  public void process() throws ServletException, IOException {
    forward(JSP_PAGE);
  }

}
