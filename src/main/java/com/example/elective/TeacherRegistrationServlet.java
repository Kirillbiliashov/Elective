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

@WebServlet("/teachers/register")
public class TeacherRegistrationServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("post teacher");
    Account acc = mapReqToAccount(req);
    Optional<Role> optRole = RoleDAO.findByName("Teacher");
    acc.setRoleId(optRole.get().getId());
    AccountDAO.save(acc);
    resp.sendRedirect("/elective/admin");
  }

  private Account mapReqToAccount(HttpServletRequest req) {
    return new Account()
        .setLogin(req.getParameter("login"))
        .setPassword(req.getParameter("password"))
        .setFirstName(req.getParameter("firstName"))
        .setLastName(req.getParameter("lastName"));
  }

}
