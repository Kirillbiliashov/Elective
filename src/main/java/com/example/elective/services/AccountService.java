package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;

import java.util.List;
import java.util.Optional;

public class AccountService extends AbstractService {

  private AccountDAO dao = new AccountDAO();

  public Optional<Account> findByCredentials(String login, String password) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.findByCredentials(login, password));
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
