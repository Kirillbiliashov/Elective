package com.example.elective;

import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("login-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    Optional<Account> optAccount = AccountDAO.findByCredentials(login, password);
    if (optAccount.isPresent()) {
      HttpSession session = req.getSession();
      session.setAttribute("account", optAccount.get());
      resp.sendRedirect("main");
      return;
    }
    req.setAttribute("errorMsg", "Login or password is incorrect");
    req.getRequestDispatcher("login-form.jsp").forward(req, resp);
  }

}
