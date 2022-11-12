package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDAO {

  public static Optional<Role> findByName(String name) {
    final String sqlStr = "SELECT * FROM role WHERE name = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setString(1, name);
      return mapResultSetToRole(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static Optional<Role> findById(int id) {
    final String sqlStr = "SELECT * FROM role WHERE id = ?";
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setInt(1, id);
      return mapResultSetToRole(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static Optional<Role> mapResultSetToRole(ResultSet rs) throws SQLException {
    if (!rs.next()) return Optional.empty();
    Role role = new Role();
    role.setId(rs.getInt("id"));
    role.setName(rs.getString("name"));
    return Optional.of(role);
  }
}
