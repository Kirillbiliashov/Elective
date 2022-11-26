package com.example.elective.servlets;

import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;

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

  private AccountService accService =  new AccountService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("login-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    Optional<Account> optAccount = accService.findByCredentials(login, password);
    if (!optAccount.isPresent()) {
      req.setAttribute("errorMsg", "Login or password is incorrect");
      req.getRequestDispatcher("login-form.jsp").forward(req, resp);
    }
    Account acc = optAccount.get();
    if (acc.isBlocked()) {
      req.setAttribute("errorMsg", "Your account is blocked");
      req.getRequestDispatcher("login-form.jsp").forward(req, resp);
    }
    HttpSession session = req.getSession();
    session.setAttribute("account", acc);
    resp.sendRedirect("main");
  }

}
