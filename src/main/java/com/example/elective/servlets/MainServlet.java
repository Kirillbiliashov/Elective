package com.example.elective.servlets;

import com.example.elective.models.Account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

/**
 * Servlet class that redirects request to user home url that depends on his/her role
 * @author Kirill Biliashov
 */

@WebServlet("/main")
public class MainServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    HttpSession session = req.getSession();
    Account account = (Account) session.getAttribute(ACCOUNT_ATTR);
    String homeUrl = LOGIN_URL;
    if (account != null) homeUrl = account.getRole().toLowerCase();
    session.setAttribute(HOME_URL_ATTR, homeUrl);
    resp.sendRedirect(homeUrl);
  }

}
