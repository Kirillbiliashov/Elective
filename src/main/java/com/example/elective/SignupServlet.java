package com.example.elective;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Account acc = mapRequestToAccount(req);
    AccountDAO.save(acc);
    resp.sendRedirect("login");
  }

  private Account mapRequestToAccount(HttpServletRequest req) {
    Account acc = new Account();
    acc.setLogin(req.getParameter("login"));
    acc.setPassword(req.getParameter("password"));
    acc.setFirstName(req.getParameter("firstName"));
    acc.setLastName(req.getParameter("lastName"));
    Optional<Role> studentRole = RoleDAO.findByName("Student");
    studentRole.ifPresent(role -> acc.setRoleId(role.getId()));
    return acc;
  }

}
