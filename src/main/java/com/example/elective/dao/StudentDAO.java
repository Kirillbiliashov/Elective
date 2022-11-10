package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Student;

import java.sql.*;

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

}
