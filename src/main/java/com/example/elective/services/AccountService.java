package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
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

  private final AccountDAO dao = daoFactory.getAccountDAO();

  public Optional<Account> findByCredentials(String login, String password)
      throws ServiceException {
    transactionManager.initTransaction(dao);
    Optional<Account> optAccount = performDaoReadOperation(() ->
        dao.findByLogin(login));
    if (!optAccount.isPresent()) return Optional.empty();
    Account acc = optAccount.get();
    return findByPassword(acc, password);
  }

  private Optional<Account> findByPassword(Account acc, String password) {
    String hashedPassword = acc.getPassword();
    return passwordsMatch(password, hashedPassword) ?
        Optional.of(acc) : Optional.empty();
  }

  public List<Account> getByRole(String roleName) throws ServiceException {
    System.out.println(this.hashCode());
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.getByRole(roleName));
  }

  public List<Account> getPaginated(String role, Pagination pagination)
      throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.getByRole(role, pagination));
  }

  public List<String> getLogins() throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(dao::getLogins);
  }

  public int getTotalCount(String roleName) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.getCountByRole(roleName));
  }

  public void save(Account acc) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.save(acc));
  }

}
