package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO {

  public static Optional<Account> getById(int id) {
    final String sqlStr = "SELECT * FROM account WHERE id = ?";
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setInt(1, id);
      return mapResultSetToOptionalAccount(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static void update(Account acc) {
    final String sqlStr = "UPDATE account SET is_blocked = ? WHERE id = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      int idx = 1;
      ps.setBoolean(idx++, acc.isBlocked());
      ps.setInt(idx, acc.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static List<Account> getByRole(String roleName) {
    final String sqlStr = "SELECT * FROM account WHERE role_id = " +
        "(SELECT id FROM role WHERE name = ?)";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setString(1, roleName);
      ResultSet rs = ps.executeQuery();
      List<Account> accounts = new ArrayList<>();
      while (rs.next()) accounts.add(mapResultSetToAccount(rs));
      return accounts;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static void save(Account acc) {
    final String sqlStr = "INSERT INTO account(login, password, first_name," +
        " last_name, role_id) VALUES(?, ?, ?, ?, ?)";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr,
        Statement.RETURN_GENERATED_KEYS)) {
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
      return mapResultSetToOptionalAccount(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static Optional<Account> mapResultSetToOptionalAccount(ResultSet rs)
      throws SQLException {
    if (!rs.next()) return Optional.empty();
    return Optional.of(mapResultSetToAccount(rs));
  }

  private static Account mapResultSetToAccount(ResultSet rs) throws SQLException {
    return new Account()
        .setId(rs.getInt("id"))
        .setLogin(rs.getString("login"))
        .setPassword(rs.getString("password"))
        .setFirstName(rs.getString("first_name"))
        .setLastName(rs.getString("last_name"))
        .setBlocked(rs.getBoolean("is_blocked"))
        .setRoleId(rs.getInt("role_id"));
  }

}
