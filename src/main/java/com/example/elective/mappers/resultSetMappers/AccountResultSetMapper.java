package com.example.elective.mappers.resultSetMappers;

import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that maps ResultSet to Account
 * @author Kirill Biliashov
 */

public class AccountResultSetMapper extends ResultSetMapper<Account> {

  private static final String USERNAME_COL_NAME = "username";
  private static final String EMAIL_COL_NAME = "email";
  private static final String PASSWORD_COL_NAME = "password";
  private static final String FIRST_NAME_COL_NAME = "first_name";
  private static final String LAST_NAME_COL_NAME = "last_name";
  private static final String ROLE_COL_NAME = "role";

  @Override
  public Account map(ResultSet rs) throws MappingException {
    try {
      return Account.newBuilder()
          .setId(rs.getInt(ID_COL_NAME))
          .setUsername(rs.getString(USERNAME_COL_NAME))
          .setEmail(rs.getString(EMAIL_COL_NAME))
          .setPassword(rs.getString(PASSWORD_COL_NAME))
          .setFirstName(rs.getString(FIRST_NAME_COL_NAME))
          .setLastName(rs.getString(LAST_NAME_COL_NAME))
          .setRole(rs.getString(ROLE_COL_NAME))
          .build();
    } catch (SQLException e) {
      throw new MappingException(e);
    }
  }

}
