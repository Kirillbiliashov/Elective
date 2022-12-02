package com.example.elective.dao;

import com.example.elective.exceptions.DAOException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.resultSetMappers.AccountResultSetMapper;
import com.example.elective.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO extends AbstractDAO<Account> {

  private static final String GET_BY_ID = "SELECT * FROM account WHERE id = ?";
  private static final String UPDATE = "UPDATE account SET is_blocked = ?" +
      " WHERE id = ?";
  private static final String FIND_BY_ROLE = "SELECT * FROM account" +
      " WHERE role_id = (SELECT id FROM role WHERE name = ?)";
  private static final String SAVE = "INSERT INTO account(login, password," +
      " first_name, last_name, role_id) VALUES(?, ?, ?, ?, ?)";
  private static final String FIND_BY_CREDENTIALS = "SELECT * FROM account" +
      " WHERE login = ? AND password = ?";

  public AccountDAO() {
    this.mapper = new AccountResultSetMapper();
  }

  @Override
  public Optional<Account> find(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      addValuesToPreparedStatement(ps, id);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException e) {
      throw new DAOException("unable to find account", e);
    }
  }

  @Override
  public void update(Account acc) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      addValuesToPreparedStatement(ps, acc.isBlocked(), acc.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("unable to update account", e);
    }
  }

  @Override
  public void delete(int id) throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Account> findAll() throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void save(Account acc) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(SAVE,
             Statement.RETURN_GENERATED_KEYS)) {
      addValuesToPreparedStatement(ps, acc.getLogin(),
          acc.getPassword(), acc.getFirstName(),
          acc.getLastName(), acc.getRoleId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) acc.getBuilder().setId(rs.getInt(1));
    } catch (SQLException e) {
      throw new DAOException("unable to save account", e);
    }
  }

  public List<Account> findByRole(String roleName) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_ROLE)) {
      addValuesToPreparedStatement(ps, roleName);
      return getEntitiesList(ps.executeQuery());
    } catch (SQLException e) {
      throw new DAOException("unable to find accounts", e);
    }
  }

  public Optional<Account> findByCredentials(String login, String password) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_CREDENTIALS)) {
      addValuesToPreparedStatement(ps, login, password);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("unable to find account", e);
    }
  }


}
