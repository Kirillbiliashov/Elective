package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;

import java.util.List;
import java.util.Optional;

public class AccountService extends AbstractService {

  private AccountDAO dao = new AccountDAO();

  public Optional<Account> findByCredentials(String login, String password) {
    transactionManager.initTransaction(dao);
    return performReadOperation(() -> dao.findByCredentials(login, password));
  }

  public List<Account> getByRole(String roleName) {
    transactionManager.initTransaction(dao);
    return performReadOperation(() -> dao.getByRole(roleName));
  }

  public void save(Account acc) {
    transactionManager.initTransaction(dao);
    dao.save(acc);
    performWriteOperation(() -> dao.save(acc));
  }

}
