package com.example.elective.mappers;

import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public abstract class AccountRequestMapper implements RequestMapper<Account>{

  @Override
  public Account map(HttpServletRequest req) {
    return Account.newBuilder()
        .setLogin(req.getParameter("login"))
        .setPassword(req.getParameter("password"))
        .setFirstName(req.getParameter("firstName"))
        .setLastName(req.getParameter("lastName"))
        .build();
  }

  protected void setAccountRole(Account acc, String roleName) {
    RoleService roleService = new RoleService();
    Optional<Role> optRole = roleService.getByName(roleName);
    optRole.ifPresent(role -> acc.getBuilder().setRoleId(role.getId()));
  }

}
