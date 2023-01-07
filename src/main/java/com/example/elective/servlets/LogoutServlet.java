package com.example.elective.servlets;

import com.example.elective.utils.Constants;
import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute(ACCOUNT_ATTR, null);
    session.setAttribute(HOME_URL_ATTR, LOGIN_URL);
    resp.sendRedirect(LOGIN_URL);
  }

}
