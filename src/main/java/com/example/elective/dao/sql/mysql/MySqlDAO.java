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

  protected Optional<T> getOptionalEntity(String sqlStr, Object... values)
      throws SQLException, MappingException {
    ResultSet rs = getResultSet(sqlStr, values);
    if (!rs.next()) return Optional.empty();
    return Optional.of(mapper.map(rs));
  }

  protected List<T> getEntitiesList(String sqlStr, Object... values)
      throws SQLException, MappingException {
    ResultSet rs = getResultSet(sqlStr, values);
    List<T> entities = new ArrayList<>();
    while (rs.next()) entities.add(mapper.map(rs));
    return entities;
  }

  protected int getCount(String sqlStr, Object... values) throws SQLException {
    final int COL_IDX = 1;
    ResultSet rs = getResultSet(sqlStr, values);
    return rs.next() ? rs.getInt(COL_IDX) : 0;
  }

  private ResultSet getResultSet(String sqlStr, Object... values)
      throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      addValuesToPreparedStatement(ps, values);
      return ps.executeQuery();
    }
  }

}
