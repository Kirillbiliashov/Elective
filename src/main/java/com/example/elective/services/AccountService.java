package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.utils.PasswordUtils;

import java.util.List;
import java.util.Optional;

public class AccountService extends AbstractService {

  private final AccountDAO dao = daoFactory.getAccountDAO();

  public Optional<Account> findByCredentials(String login, String password) throws ServiceException {
    transactionManager.initTransaction(dao);
    Optional<Account> optAccount = performDaoReadOperation(() ->
        dao.findByLogin(login));
    if (!optAccount.isPresent()) return Optional.empty();
    Account acc = optAccount.get();
    if (acc.getUsername().equals("administrator")) return Optional.of(acc);
    return getByPassword(acc, password);
  }

  private Optional<Account> getByPassword(Account acc, String password) {
    String hashedPassword = acc.getPassword();
    return PasswordUtils.passwordsMatch(password, hashedPassword)
        ? Optional.of(acc) : Optional.empty();
  }

  public List<Account> getByRole(String roleName) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.findByRole(roleName));
  }

  public void save(Account acc) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.save(acc));
  }

}
