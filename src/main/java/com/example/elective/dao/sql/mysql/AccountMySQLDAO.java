package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.sql.AbstractDAO;
import com.example.elective.models.Account;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Class that implements methods of AccountDAO for MySQL database
 * @author Kirill Biliashov
 */

public class AccountMySQLDAO extends AbstractDAO implements AccountDAO {

  private static final String GET_BY_ROLE = "SELECT a FROM Account a " +
      "WHERE role = :role";
  private static final String GET_LOGINS = "SELECT " +
      "CONCAT(a.username, ',', a.email) FROM Account a ";
  private static final String FIND_BY_LOGIN = "SELECT a FROM Account a WHERE " +
      "a.email = :login OR a.username = :login";
  private static final String GET_COUNT  = "SELECT COUNT(a) from Account a" +
      " WHERE a.role = :role";

  @Override
  public Optional<Account> find(int id) {
    return Optional.ofNullable(session.get(Account.class, id));
  }

  @Override
  public void update(Account acc) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Account> getAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void save(Account acc) {
    session.persist(acc);
  }

  @Override
  public List<Account> getByRole(String role) {
    return session
        .createQuery(GET_BY_ROLE, Account.class)
        .setParameter("role", role)
        .getResultList();
  }

  @Override
  public List<Account> getByRole(String roleName, Pagination pagination) {
    return session.createQuery(GET_BY_ROLE, Account.class)
        .setParameter("role", roleName)
        .setFirstResult(pagination.getFrom())
        .setMaxResults(pagination.getDisplayCount())
        .getResultList();
  }

  @Override
  public List<String> getLogins() {
    return session
        .createQuery(GET_LOGINS, String.class)
        .getResultList();
  }

  @Override
  public Optional<Account> findByLogin(String login) {
    return Optional.ofNullable(session
        .createQuery(FIND_BY_LOGIN, Account.class)
        .getSingleResult());
  }

  @Override
  public int getCountByRole(String roleName) {
    return session
        .createQuery(GET_COUNT, Integer.class)
        .setParameter("role", roleName)
        .getSingleResult();
  }

}
