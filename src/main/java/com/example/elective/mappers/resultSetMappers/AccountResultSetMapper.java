package com.example.elective.mappers.resultSetMappers;

import com.example.elective.mappers.Mapper;
import com.example.elective.models.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountResultSetMapper extends ResultSetMapper<Account> {

  private static final String LOGIN_COL_NAME = "login";
  private static final String PASSWORD_COL_NAME = "password";
  private static final String FIRST_NAME_COL_NAME = "first_name";
  private static final String LAST_NAME_COL_NAME = "last_name";
  private static final String IS_BLOCKED_COL_NAME = "is_blocked";
  private static final String ROLE_ID_COL_NAME = "role_id";

  @Override
  public Account map(ResultSet rs) {
    try {
      return Account.newBuilder()
          .setId(rs.getInt(ID_COL_NAME))
          .setLogin(rs.getString(LOGIN_COL_NAME))
          .setPassword(rs.getString(PASSWORD_COL_NAME))
          .setFirstName(rs.getString(FIRST_NAME_COL_NAME))
          .setLastName(rs.getString(LAST_NAME_COL_NAME))
          .setBlocked(rs.getBoolean(IS_BLOCKED_COL_NAME))
          .setRoleId(rs.getInt(ROLE_ID_COL_NAME))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
