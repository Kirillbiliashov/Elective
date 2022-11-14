package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Account;

import java.sql.*;
import java.util.Optional;

public class AccountDAO {

  public static void save(Account acc) {
    final String sqlStr = "INSERT INTO account(login, password, first_name," +
        " last_name, role_id) VALUES(?, ?, ?, ?, ?)";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
      addMissingValuesToStatement(ps, acc);
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) acc.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static void addMissingValuesToStatement(PreparedStatement ps, Account acc) throws SQLException {
    int idx = 1;
    ps.setString(idx++, acc.getLogin());
    ps.setString(idx++, acc.getPassword());
    ps.setString(idx++, acc.getFirstName());
    ps.setString(idx++, acc.getLastName());
    ps.setInt(idx++, acc.getRoleId());
  }

  public static Optional<Account> findByCredentials(String login, String password) {
    final String sqlStr = "SELECT * FROM account WHERE login = ? AND password = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      int idx = 1;
      ps.setString(idx++, login);
      ps.setString(idx++, password);
      return mapResultSetToAccount(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static Optional<Account> mapResultSetToAccount(ResultSet rs) throws SQLException {
    if (!rs.next()) return Optional.empty();
    return Optional.of(new Account()
        .setLogin(rs.getString("login"))
        .setPassword(rs.getString("password"))
        .setFirstName(rs.getString("first_name"))
        .setLastName(rs.getString("last_name"))
        .setBlocked(rs.getBoolean("is_blocked"))
        .setRoleId(rs.getInt("role_id")));
  }

}
