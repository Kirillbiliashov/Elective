package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoleDAO extends AbstractDAO<Role> {

  private static final String FIND_BY_NAME = "SELECT * FROM role" +
      " WHERE name = ?";
  private static final String FIND_BY_ID = "SELECT * FROM role WHERE id = ?";

  public Optional<Role> findByName(String name) {
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(FIND_BY_NAME)) {
      ps.setString(1, name);
      return mapResultSetToRole(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public Optional<Role> find(int id) {
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {
      ps.setInt(1, id);
      return mapResultSetToRole(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public List<Role> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void save(Role entity) {

  }

  @Override
  public void update(Role entity) {

  }

  @Override
  public void delete(int id) {

  }

  private Optional<Role> mapResultSetToRole(ResultSet rs) throws SQLException {
    if (!rs.next()) return Optional.empty();
    Role role = new Role(rs.getInt("id"), rs.getString("name"));
    return Optional.of(role);
  }

}
