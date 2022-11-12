package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Student;
import com.example.elective.models.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TeacherDAO {

  public static Optional<Teacher> findByAccountId(int accId) {
    final String sqlStr = "SELECT * FROM teacher WHERE account_id = ? ";
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setInt(1, accId);
      return mapResultSetToTeacher(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static Optional<Teacher> mapResultSetToTeacher(ResultSet rs)
      throws SQLException {
    if (rs.next()) return Optional.of(new Teacher()
        .setId(rs.getInt("id"))
        .setFirstName(rs.getString("name"))
        .setLastName(rs.getString("last_name"))
        .setAccountId(rs.getInt("account_id")));
    return Optional.empty();
  }

}
