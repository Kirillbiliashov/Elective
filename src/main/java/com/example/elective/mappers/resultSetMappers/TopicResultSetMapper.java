package com.example.elective.mappers.resultSetMappers;

import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicResultSetMapper extends ResultSetMapper<Topic> {

  private static final String NAME_COL_NAME = "name";

  @Override
  public Topic map(ResultSet rs) throws MappingException {
    try {
      return new Topic(rs.getInt(ID_COL_NAME), rs.getString(NAME_COL_NAME));
    } catch (SQLException e) {
      throw new MappingException(e);
    }
  }

}
