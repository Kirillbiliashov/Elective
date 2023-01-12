package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;

import java.util.*;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

public class StudentService extends AbstractService {

  public void changeBlockStatus(int id) throws ServiceException {
    final AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = new TransactionManager();
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
