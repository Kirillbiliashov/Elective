package com.example.elective.mappers;

import com.example.elective.models.Account;

import javax.servlet.http.HttpServletRequest;

public class AccountRequestMapper implements RequestMapper<Account>{

  @Override
  public Account map(HttpServletRequest req) {
    return new Account()
        .setLogin(req.getParameter("login"))
        .setPassword(req.getParameter("password"))
        .setFirstName(req.getParameter("firstName"))
        .setLastName(req.getParameter("lastName"));
  }

}
