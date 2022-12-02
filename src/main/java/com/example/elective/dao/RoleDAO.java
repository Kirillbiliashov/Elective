package com.example.elective.dao;

import com.example.elective.exceptions.DAOException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.resultSetMappers.RoleResultSetMapper;
import com.example.elective.models.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoleDAO extends AbstractDAO<Role> {

  private static final String FIND_BY_NAME = "SELECT * FROM role" +
      " WHERE name = ?";
  private static final String FIND = "SELECT * FROM role WHERE id = ?";

  public RoleDAO() {
    this.mapper = new RoleResultSetMapper();
  }

  public Optional<Role> findByName(String name) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_NAME)) {
      addValuesToPreparedStatement(ps, name);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("unable to find role by its name", e);
    }
  }

  @Override
  public Optional<Role> find(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND)) {
      addValuesToPreparedStatement(ps, id);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("unable to find role", e);
    }
  }

  @Override
  public List<Role> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void save(Role entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(Role entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(int id) {
    throw new UnsupportedOperationException();
  }

}
