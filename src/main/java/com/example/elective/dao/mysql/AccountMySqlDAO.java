package com.example.elective.dao.mysql;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.AccountResultSetMapper;
import com.example.elective.models.Account;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AccountMySqlDAO extends MySqlDAO<Account> implements AccountDAO {

  private static final String GET_BY_ID = "SELECT * FROM account WHERE id = ?";
  private static final String UPDATE = "UPDATE account SET is_blocked = ?" +
      " WHERE id = ?";
  private static final String FIND_BY_ROLE = "SELECT * FROM account" +
      " WHERE role_id = (SELECT id FROM role WHERE name = ?)";
  private static final String SAVE = "INSERT INTO account(login, password," +
      " first_name, last_name, role_id) VALUES(?, ?, ?, ?, ?)";
  private static final String FIND_BY_LOGIN = "SELECT * FROM account" +
      " WHERE login = ?";

  public AccountMySqlDAO() {
    this.mapper = new AccountResultSetMapper();
  }

  @Override
  public Optional<Account> find(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      addValuesToPreparedStatement(ps, id);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
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
      String hashedPassword = Utils.hashPassword(acc.getPassword());
      addValuesToPreparedStatement(ps, acc.getLogin(),
          hashedPassword, acc.getFirstName(),
          acc.getLastName(), acc.getRoleId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) acc.getBuilder().setId(rs.getInt(1));
    } catch (SQLException e) {
      throw new DAOException("unable to save account", e);
    }
  }

  @Override
  public List<Account> findByRole(String roleName) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_ROLE)) {
      addValuesToPreparedStatement(ps, roleName);
      return getEntitiesList(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      throw new DAOException("unable to find accounts", e);
    }
  }

  @Override
  public Optional<Account> findByLogin(String login) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_LOGIN)) {
      addValuesToPreparedStatement(ps, login);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
      throw new DAOException("unable to find account", e);
    }
  }

}
