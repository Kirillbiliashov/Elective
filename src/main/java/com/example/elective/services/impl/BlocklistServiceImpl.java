package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Blocklist;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.BlocklistService;

import java.util.*;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

public class BlocklistServiceImpl extends AbstractService implements BlocklistService {

  @Override
  public void changeBlockStatus(int id) throws ServiceException {
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> {
      Optional<Blocklist> optBlocklist = dao.find(id);
      if (optBlocklist.isPresent()) dao.delete(id);
      else dao.save(id);
    });
  }

  @Override
  public Optional<Blocklist> getBlockStatus(TransactionManager tm, int id)
      throws DAOException {
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    tm.initTransaction(dao);
    return dao.find(id);
  }

  @Override
  public Optional<Blocklist> getBlockStatus(int id) throws ServiceException {
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.find(id));
  }

}
