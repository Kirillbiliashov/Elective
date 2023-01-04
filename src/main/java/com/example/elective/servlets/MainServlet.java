package com.example.elective.servlets;

import com.example.elective.models.Account;
import com.example.elective.utils.RequestUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    HttpSession session = req.getSession();
    Account account = (Account) session.getAttribute("account");
    String homeUrl = "login";
    if (account != null) homeUrl = account.getRole().toLowerCase();
    session.setAttribute("homeUrl",  homeUrl);
    resp.sendRedirect(homeUrl);
  }

}
