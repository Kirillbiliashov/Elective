package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.AccountResultSetMapper;
import com.example.elective.models.Account;
import com.example.elective.selection.Pagination;
import com.example.elective.utils.PasswordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountMySqlDAO extends MySqlDAO<Account> implements AccountDAO {

  private static final String SELECT_ALL = "SELECT * FROM account";
  private static final String FIND_BY_ID = SELECT_ALL + " WHERE id = ?";
  private static final String UPDATE = "UPDATE account SET is_blocked = ?" +
      " WHERE id = ?";
  private static final String GET_BY_ROLE = SELECT_ALL + " WHERE role = ?";
  private static final String GET_BY_ROLE_AT_PAGE = GET_BY_ROLE + " LIMIT ?,?";
  private static final String SAVE = "INSERT INTO account" +
      "(username, email, password, first_name, last_name, role)" +
      " VALUES(?, ?, ?, ?, ?, ?)";
  private static final String FIND_BY_LOGIN = SELECT_ALL +
      " WHERE username = ? OR email = ?";
  private static final String GET_COUNT_BY_ROLE = "SELECT COUNT(*) FROM account" +
      " WHERE role = ?";
  private static final String LOGIN_COL = "login";
  private static final String SELECT_LOGINS = "SELECT CONCAT(username, ','," +
      " email) AS " + LOGIN_COL + " FROM account";

  public AccountMySqlDAO() {
    this.mapper = new AccountResultSetMapper();
  }

  @Override
  public Optional<Account> find(int id) throws DAOException {
    try {
      return getOptionalEntity(FIND_BY_ID, id);
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
  public List<Account> getAll() throws DAOException {
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
  public List<Account> getByRole(String role) throws DAOException {
    try {
      return getEntitiesList(GET_BY_ROLE, role);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find accounts", e);
    }
  }

  @Override
  public List<Account> getByRole(String roleName, Pagination pagination)
      throws DAOException {
    try {
      return getEntitiesList(GET_BY_ROLE_AT_PAGE, roleName,
          pagination.getFrom(), pagination.getDisplayCount());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find accounts", e);
    }
  }

  @Override
  public List<String> getLogins() throws DAOException {
    try (Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(SELECT_LOGINS);
      List<String> logins = new ArrayList<>();
      while (rs.next()) logins.add(rs.getString(LOGIN_COL));
      return logins;
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to retrieve logins", e);
    }
  }

  @Override
  public Optional<Account> findByLogin(String login) throws DAOException {
    try {
      return getOptionalEntity(FIND_BY_LOGIN, login, login);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find account", e);
    }
  }

  @Override
  public int getCountByRole(String roleName) throws DAOException {
    try {
      return getCount(GET_COUNT_BY_ROLE, roleName);
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find account", e);
    }
  }

}
