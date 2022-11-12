package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Account;

import java.sql.*;
import java.util.Optional;

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

  public static Optional<Account> findByCredentials(String login, String password) {
    final String sqlStr = "SELECT * FROM account WHERE login = ? AND password = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      int idx = 1;
      ps.setString(idx++, login);
      ps.setString(idx++, password);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        int id = rs.getInt(1);
        Account acc = new Account();
        acc.setId(id);
        acc.setPassword(password);
        acc.setLogin(login);
        return Optional.of(acc);
      }
      return Optional.empty();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

}
