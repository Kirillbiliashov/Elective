package com.example.elective.mappers.resultSetMappers;

import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleResultSetMapper extends ResultSetMapper<Role> {

  private static final String NAME_COL_NAME = "name";

  @Override
  public Role map(ResultSet rs) throws MappingException {
    try {
      return new Role(rs.getInt(ID_COL_NAME), rs.getString(NAME_COL_NAME));
    } catch (SQLException e) {
      throw new MappingException(e);
    }
  }

}
