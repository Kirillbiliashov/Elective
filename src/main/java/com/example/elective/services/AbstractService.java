package com.example.elective.services;

import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.dao.DAOFactory;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.exceptions.ServiceException;


/**
 * Abstract base class for all service classes that provides utility methods for them
 * @author Kirill Biliashov
 */

public abstract class AbstractService {

  protected DAOFactory daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL);

  protected <T> T read(TransactionManager tm, DAOReader<T> reader)
      throws ServiceException {
    try {
      T res = reader.read();
      tm.commitTransaction();
      return res;
    } catch (DAOException | MappingException e) {
      e.printStackTrace();
      tm.rollbackTransaction();
      throw new ServiceException(e);
    } finally {
      tm.endTransaction();
    }
  }

  protected void write(TransactionManager tm, DAOWriter writer)
      throws ServiceException {
    try {
      writer.write();
      tm.commitTransaction();
    } catch (DAOException e) {
      e.printStackTrace();
      tm.rollbackTransaction();
      throw new ServiceException(e);
    } finally {
      tm.endTransaction();
    }
  }

}
