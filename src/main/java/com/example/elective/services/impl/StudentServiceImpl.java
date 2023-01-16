package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.StudentService;

import java.util.*;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

public class StudentServiceImpl extends AbstractService implements StudentService {

  @Override
  public void changeBlockStatus(int id) throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> {
      Optional<Account> optAcc = dao.find(id);
      if (optAcc.isPresent()) {
        Account acc = optAcc.get();
        acc.getBuilder().setBlocked(!acc.isBlocked());
        dao.update(acc);
      }
    });
  }

}
