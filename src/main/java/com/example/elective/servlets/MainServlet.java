package com.example.elective.servlets;

import com.example.elective.dao.RoleDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.RoleService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

@WebServlet("/")
public class MainServlet extends HttpServlet {

  private RoleService roleService = new RoleService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    HttpSession session = req.getSession();
    Account account = (Account) session.getAttribute("account");
    int roleId = account.getRoleId();
    Optional<Role> optRole = Optional.empty();
    try {
      optRole = roleService.getById(roleId);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    String homeUrl = getHomeUrl(optRole);
    session.setAttribute("homeUrl",  homeUrl);
    resp.sendRedirect(homeUrl);
  }

  private String getHomeUrl(Optional<Role> optRole) {
    String homeUrl = "/elective/login";
    if (optRole.isPresent()) {
      Role role = optRole.get();
      homeUrl = role.getName().toLowerCase();
    }
    return homeUrl;
  }

}
