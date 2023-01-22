package com.example.elective.services.interfaces;

import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Blocklist;

import java.util.Optional;

/**
 * Interface describing methods that student service should implement
 * @author Kirill Biliashov
 */

public interface BlocklistService {

  void changeBlockStatus(int id) throws ServiceException;
  Optional<Blocklist> getBlockStatus(TransactionManager tm, int id) throws DAOException;
  Optional<Blocklist> getBlockStatus(int id) throws ServiceException;
}
