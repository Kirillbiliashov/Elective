package com.example.elective.mappers.requestMappers;

import com.example.elective.models.Account;

import javax.servlet.http.HttpServletRequest;

public class AccountRequestMapper implements RequestMapper<Account> {

  private static final String USERNAME = "username";
  private static final String EMAIL = "email";
  private static final String PASSWORD = "password";
  private static final String FIRSTNAME = "firstName";
  private static final String LASTNAME = "lastName";
  private static final String ROLE = "role";

  @Override
  public Account map(HttpServletRequest req) {
    return Account.newBuilder()
        .setUsername(req.getParameter(USERNAME))
        .setEmail(req.getParameter(EMAIL))
        .setPassword(req.getParameter(PASSWORD))
        .setFirstName(req.getParameter(FIRSTNAME))
        .setLastName(req.getParameter(LASTNAME))
        .setRole(req.getParameter(ROLE))
        .build();
  }

}
