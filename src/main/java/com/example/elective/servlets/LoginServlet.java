package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;
import com.example.elective.services.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.ServerSocket;
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
    Optional<Account> optAccount = Optional.empty();
    try {
      optAccount = accService.findByCredentials(login, password);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    if (!optAccount.isPresent()) handleAbsentAccount(req, resp);
    Account acc = optAccount.get();
    if (acc.isBlocked()) handleBlockedAccount(req, resp);
    addAccountToSession(req, acc);
    resp.sendRedirect(Utils.getRedirectUrl(req, "main"));
  }

  private void handleAbsentAccount(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("errorMsg", "Login or password is incorrect");
    req.getRequestDispatcher("login-form.jsp").forward(req, resp);
  }

  private void handleBlockedAccount(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("errorMsg", "Your account is blocked");
    req.getRequestDispatcher("login-form.jsp").forward(req, resp);
  }

  private void addAccountToSession(HttpServletRequest req, Account acc) {
    HttpSession session = req.getSession();
    session.setAttribute("account", acc);
  }

}
