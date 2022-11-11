package com.example.elective;

import com.example.elective.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    boolean isAccFound = AccountDAO.findByLoginAndPassword(login, password);
    if (isAccFound) {
      resp.sendRedirect("/elective/main");
    } else {
      req.setAttribute("errorMsg", "Incorrect login or password");
      req.getRequestDispatcher("login-form.jsp").forward(req, resp);
    }
  }

}
