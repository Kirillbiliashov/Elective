package com.example.elective.mappers;

import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public abstract class AccountRequestMapper implements RequestMapper<Account>{

  @Override
  public Account map(HttpServletRequest req) {
    return new Account()
        .setLogin(req.getParameter("login"))
        .setPassword(req.getParameter("password"))
        .setFirstName(req.getParameter("firstName"))
        .setLastName(req.getParameter("lastName"));
  }

  protected void setAccountRole(Account acc, String roleName) {
    Optional<Role> optRole = RoleDAO.findByName(roleName);
    optRole.ifPresent(role -> acc.setRoleId(role.getId()));
  }

}
