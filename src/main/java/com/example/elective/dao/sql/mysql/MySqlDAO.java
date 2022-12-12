package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.sql.AbstractDAO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MySqlDAO<T> extends AbstractDAO {

  protected Mapper<ResultSet, T> mapper;

  protected void addValuesToPreparedStatement(PreparedStatement ps, Object... values)
      throws SQLException {
    int idx = 1;
    for (final Object value: values) {
      ps.setObject(idx++, value);
    }
  }

  protected Optional<T> getOptionalEntity(ResultSet rs) throws SQLException, MappingException {
    if (!rs.next()) return Optional.empty();
    return Optional.of(mapper.map(rs));
  }

  protected List<T> getEntitiesList(ResultSet rs) throws SQLException, MappingException {
    List<T> entities = new ArrayList<>();
    while (rs.next()) entities.add(mapper.map(rs));
    return entities;
  }

}
