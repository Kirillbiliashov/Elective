package com.example.elective.mappers;

import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class TeacherRequestMapper extends AccountRequestMapper {

  @Override
  public Account map(HttpServletRequest req) {
    Account acc = super.map(req);
    setAccountRole(acc, "Teacher");
    return acc;
  }

}
