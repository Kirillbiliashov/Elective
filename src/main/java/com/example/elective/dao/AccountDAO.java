package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Account;

import java.sql.*;

public class AccountDAO {
  public static void save(Account account) {
    final String sqlStr = "INSERT INTO account(login, password) VALUES(?, ?)";
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
      int idx = 1;
      ps.setString(idx++, account.getLogin());
      ps.setString(idx++, account.getPassword());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) account.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static boolean findByLoginAndPassword(String login, String password) {
    final String sqlStr = "SELECT * FROM account WHERE login = ? AND password = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      int idx = 1;
      ps.setString(idx++, login);
      ps.setString(idx++, password);
      ResultSet rs = ps.executeQuery();
      return rs.next();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }
}
