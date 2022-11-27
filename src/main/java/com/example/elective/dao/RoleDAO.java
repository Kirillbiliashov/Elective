package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDAO {

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

  public Optional<Role> findById(int id) {
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {
      ps.setInt(1, id);
      return mapResultSetToRole(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private Optional<Role> mapResultSetToRole(ResultSet rs) throws SQLException {
    if (!rs.next()) return Optional.empty();
    Role role = new Role();
    role.setId(rs.getInt("id"));
    role.setName(rs.getString("name"));
    return Optional.of(role);
  }

}
