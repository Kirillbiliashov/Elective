package com.example.elective.servlets.admin;

import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.requestMappers.AccountRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.AccountService;
import com.example.elective.services.RoleService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/teachers/register")
public class TeacherRegistrationServlet extends HttpServlet {

  private RequestMapper<Account> accountMapper = new AccountRequestMapper();
  private AccountService accService;
  private RoleService roleService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    accService = (AccountService) context.getAttribute("accountService");
    roleService = (RoleService) context.getAttribute("roleService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      Optional<Role> optRole = roleService.getByName("Teacher");
      optRole.ifPresent(role -> req.setAttribute("roleId", role.getId()));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Account acc = accountMapper.map(req);
    try {
      accService.save(acc);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(Utils.getRedirectUrl(req, "/elective/admin/teachers"));
  }

}
