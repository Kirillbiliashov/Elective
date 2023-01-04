package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;

import java.util.*;

public class StudentService extends AbstractService {

  private AccountDAO accDao = daoFactory.getAccountDAO();

  public void changeBlockStatus(int id) throws ServiceException {
    transactionManager.initTransaction(accDao);
    performDaoWriteOperation(() -> {
      Optional<Account> optAcc = accDao.find(id);
      if (optAcc.isPresent()) {
        Account acc = optAcc.get();
        acc.getBuilder().setBlocked(!acc.isBlocked());
        accDao.update(acc);
      }
    });
  }

}
