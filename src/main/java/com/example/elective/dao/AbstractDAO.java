package com.example.elective.dao;

import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;
import com.example.elective.models.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Entity> implements DAO<T> {

  protected Connection conn;
  protected Mapper<ResultSet, T> mapper;

  public void setConnection(Connection conn) {
    this.conn = conn;
  }

  protected void addValuesToPreparedStatement(PreparedStatement ps, Object... values)
      throws SQLException {
    int idx = 1;
    for (final Object value: values) {
      ps.setObject(idx++, value);
    }
  }

  protected Optional<T> getOptionalEntity(ResultSet rs)
      throws SQLException {
    if (!rs.next()) return Optional.empty();
    return Optional.of(mapper.map(rs));
  }

  protected List<T> getEntitiesList(ResultSet rs) throws SQLException {
    List<T> entities = new ArrayList<>();
    while (rs.next()) entities.add(mapper.map(rs));
    return entities;
  }

}
