package com.example.elective.mappers.resultSetMappers;

import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Blocklist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlocklistResultSetMapper extends ResultSetMapper<Blocklist> {

  private static final String STUDENT_ID_COL_NAME = "student_id";

  @Override
  public Blocklist map(ResultSet rs) throws MappingException {
    try {
      return new Blocklist(rs.getInt(ID_COL_NAME),
          rs.getInt(STUDENT_ID_COL_NAME));
    } catch (SQLException e) {
      throw new MappingException(e);
    }
  }
}
