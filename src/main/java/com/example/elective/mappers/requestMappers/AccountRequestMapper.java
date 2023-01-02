package com.example.elective.mappers.requestMappers;

import com.example.elective.models.Account;

import javax.servlet.http.HttpServletRequest;

public class AccountRequestMapper implements RequestMapper<Account> {

  @Override
  public Account map(HttpServletRequest req) {
    return Account.newBuilder()
        .setUsername(req.getParameter("username"))
        .setEmail(req.getParameter("email"))
        .setPassword(req.getParameter("password"))
        .setFirstName(req.getParameter("firstName"))
        .setLastName(req.getParameter("lastName"))
        .setRole(req.getParameter("role"))
        .build();
  }

}
