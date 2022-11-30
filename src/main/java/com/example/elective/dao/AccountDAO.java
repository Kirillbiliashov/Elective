package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO extends AbstractDAO<Account> {

  private static final String GET_BY_ID = "SELECT * FROM account WHERE id = ?";
  private static final String UPDATE = "UPDATE account SET is_blocked = ?" +
      " WHERE id = ?";
  private static final String GET_BY_ROLE = "SELECT * FROM account" +
      " WHERE role_id = (SELECT id FROM role WHERE name = ?)";
  private static final String SAVE = "INSERT INTO account(login, password," +
      " first_name, last_name, role_id) VALUES(?, ?, ?, ?, ?)";
  private static final String FIND_BY_CREDENTIALS = "SELECT * FROM account" +
      " WHERE login = ? AND password = ?";

  @Override
  public Optional<Account> find(int id) {
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      ps.setInt(1, id);
      return mapResultSetToOptionalAccount(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public void update(Account acc) {
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      int idx = 1;
      ps.setBoolean(idx++, acc.isBlocked());
      ps.setInt(idx, acc.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public void delete(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Account> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void save(Account acc) {
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(SAVE,
             Statement.RETURN_GENERATED_KEYS)) {
      addMissingValuesToStatement(ps, acc);
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) acc.getBuilder().setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public List<Account> getByRole(String roleName) {
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(GET_BY_ROLE)) {
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

  private void addMissingValuesToStatement(PreparedStatement ps, Account acc)
      throws SQLException {
    int idx = 1;
    ps.setString(idx++, acc.getLogin());
    ps.setString(idx++, acc.getPassword());
    ps.setString(idx++, acc.getFirstName());
    ps.setString(idx++, acc.getLastName());
    ps.setInt(idx, acc.getRoleId());
  }

  public Optional<Account> findByCredentials(String login, String password) {
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(FIND_BY_CREDENTIALS)) {
      int idx = 1;
      ps.setString(idx++, login);
      ps.setString(idx, password);
      return mapResultSetToOptionalAccount(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private Optional<Account> mapResultSetToOptionalAccount(ResultSet rs)
      throws SQLException {
    if (!rs.next()) return Optional.empty();
    return Optional.of(mapResultSetToAccount(rs));
  }

  private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
    return Account.newBuilder()
        .setId(rs.getInt("id"))
        .setLogin(rs.getString("login"))
        .setPassword(rs.getString("password"))
        .setFirstName(rs.getString("first_name"))
        .setLastName(rs.getString("last_name"))
        .setBlocked(rs.getBoolean("is_blocked"))
        .setRoleId(rs.getInt("role_id"))
        .build();
  }

}
