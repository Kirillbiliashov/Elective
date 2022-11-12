package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Student;

import java.sql.*;
import java.util.Optional;

public class StudentDAO {

  public static void save(Student student) {
    final String sqlStr = "INSERT INTO student(first_name, last_name, account_id) VALUES(?, ?, ?)";
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
      int idx = 1;
      ps.setString(idx++, student.getFirstName());
      ps.setString(idx++, student.getLastName());
      ps.setInt(idx++, student.getAccountId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) student.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static Optional<Student> findByAccountId(int accId) {
    final String sqlStr = "SELECT * FROM student WHERE account_id = ? ";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setInt(1, accId);
      return mapResultSetToStudent(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static Optional<Student> mapResultSetToStudent(ResultSet rs)
      throws SQLException {
    if (rs.next()) return Optional.of(new Student()
        .setId(rs.getInt("id"))
        .setFirstName(rs.getString("first_name"))
        .setLastName(rs.getString("last_name"))
        .setAccountId(rs.getInt("account_id"))
        .setBlocked(rs.getBoolean("is_blocked")));
    return Optional.empty();
  }

}
