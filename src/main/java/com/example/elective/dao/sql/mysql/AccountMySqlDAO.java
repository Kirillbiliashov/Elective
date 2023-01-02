package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.AccountResultSetMapper;
import com.example.elective.models.Account;
import com.example.elective.utils.PasswordUtils;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AccountMySqlDAO extends MySqlDAO<Account> implements AccountDAO {

  private static final String GET_BY_ID = "SELECT * FROM account WHERE id = ?";
  private static final String UPDATE = "UPDATE account SET is_blocked = ?" +
      " WHERE id = ?";
  private static final String FIND_BY_ROLE = "SELECT * FROM account" +
      " WHERE role = ?";
  private static final String SAVE = "INSERT INTO account(username, email, password," +
      " first_name, last_name, role) VALUES(?, ?, ?, ?, ?, ?)";
  private static final String FIND_BY_LOGIN = "SELECT * FROM account WHERE username = ? OR email = ?";

  public AccountMySqlDAO() {
    this.mapper = new AccountResultSetMapper();
  }

  @Override
  public Optional<Account> find(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      addValuesToPreparedStatement(ps, id);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find account", e);
    }
  }

  @Override
  public void update(Account acc) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      addValuesToPreparedStatement(ps, acc.isBlocked(), acc.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
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
      String hashedPassword = PasswordUtils.hashPassword(acc.getPassword());
      addValuesToPreparedStatement(ps, acc.getUsername(),
          acc.getEmail(), hashedPassword, acc.getFirstName(),
          acc.getLastName(), acc.getRole());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) acc.getBuilder().setId(rs.getInt(1));
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to save account", e);
    }
  }

  @Override
  public List<Account> findByRole(String role) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_ROLE)) {
      addValuesToPreparedStatement(ps, role);
      return getEntitiesList(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find accounts", e);
    }
  }

  @Override
  public Optional<Account> findByLogin(String login) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_LOGIN)) {
      addValuesToPreparedStatement(ps, login, login);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find account", e);
    }
  }

}
