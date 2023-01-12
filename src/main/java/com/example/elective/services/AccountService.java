package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

import static com.example.elective.utils.PasswordUtils.passwordsMatch;

/**
 * Class containing business logic methods regarding accounts
 * @author Kirill Biliashov
 */

public class AccountService extends AbstractService {

  public Optional<Account> findByCredentials(String login, String password)
      throws ServiceException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    Optional<Account> optAcc = read(tm, () -> dao.findByLogin(login));
    if (!optAcc.isPresent()) return Optional.empty();
    Account acc = optAcc.get();
    return findByPassword(acc, password);
  }

  private Optional<Account> findByPassword(Account acc, String password) {
    String hashedPassword = acc.getPassword();
    return passwordsMatch(password, hashedPassword) ?
        Optional.of(acc) : Optional.empty();
  }

  public List<Account> getByRole(String roleName) throws ServiceException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getByRole(roleName));
  }

  public List<Account> getPaginated(String role, Pagination pagination)
      throws ServiceException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getByRole(role, pagination));
  }

  public List<String> getLogins() throws ServiceException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, dao::getLogins);
  }

  public int getTotalCount(String roleName) throws ServiceException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getCountByRole(roleName));
  }

  public void save(Account acc) throws ServiceException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> dao.save(acc));
  }

  protected Optional<Account> find(TransactionManager tm, int id)
      throws DAOException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    tm.initTransaction(dao);
    return dao.find(id);
  }

}
