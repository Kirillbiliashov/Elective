package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;

import java.util.*;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

public class StudentService extends AbstractService {

  private final AccountDAO accDao = daoFactory.getAccountDAO();

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
