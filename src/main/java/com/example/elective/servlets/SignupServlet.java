package com.example.elective.servlets;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.requestMappers.AccountRequestMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.AccountService;
import com.example.elective.services.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

  private Mapper<HttpServletRequest, Account> accountMapper =
      new AccountRequestMapper();
  private AccountService accService = new AccountService();
  private RoleService roleService = new RoleService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      Optional<Role> optRole = roleService.getByName("Student");
      optRole.ifPresent(role -> req.setAttribute("roleId", role.getId()));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Account acc = accountMapper.map(req);
    try {
      accService.save(acc);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect("login");
  }

}
