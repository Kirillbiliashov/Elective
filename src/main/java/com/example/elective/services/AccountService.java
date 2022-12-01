package com.example.elective.services;

import com.example.elective.TransactionManager;
import com.example.elective.dao.AbstractDAO;
import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;

import java.util.List;
import java.util.Optional;

public class AccountService {

  private AccountDAO dao = new AccountDAO();
  private TransactionManager transactionManager = new TransactionManager();

  public Optional<Account> findByCredentials(String login, String password) {
    transactionManager.initTransaction(dao);
    Optional<Account> optAccounts = dao.findByCredentials(login, password);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return optAccounts;
  }

  public List<Account> getByRole(String roleName) {
    transactionManager.initTransaction(dao);
    List<Account> accountList = dao.getByRole(roleName);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return accountList;
  }

  public void save(Account acc) {
    transactionManager.initTransaction(dao);
    dao.save(acc);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
  }

}
