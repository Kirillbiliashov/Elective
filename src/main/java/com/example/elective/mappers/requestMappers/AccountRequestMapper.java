package com.example.elective.mappers.requestMappers;

import com.example.elective.models.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AccountRequestMapper implements RequestMapper<Account> {

  @Override
  public Account map(HttpServletRequest req) {
    return Account.newBuilder()
        .setLogin(req.getParameter("login"))
        .setPassword(req.getParameter("password"))
        .setFirstName(req.getParameter("firstName"))
        .setLastName(req.getParameter("lastName"))
        .setRoleId(Integer.parseInt(req.getParameter("roleId")))
        .build();
  }

}
