package com.example.elective.mappers.requestMappers;

import com.example.elective.mappers.requestMappers.AccountRequestMapper;
import com.example.elective.models.Account;

import javax.servlet.http.HttpServletRequest;

public class TeacherRequestMapper extends AccountRequestMapper {

  @Override
  public Account map(HttpServletRequest req) {
    Account acc = super.map(req);
    setAccountRole(acc, "Teacher");
    return acc;
  }

}
