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

  protected TransactionManager transactionManager = new TransactionManager();
  protected DAOFactory daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL);

  protected <T> T performDaoReadOperation(DAOReadOperation<T> operation)
      throws ServiceException {
    try {
      T res = operation.read();
      transactionManager.commitTransaction();
      return res;
    } catch (DAOException | MappingException e) {
      e.printStackTrace();
      transactionManager.rollbackTransaction();
      throw new ServiceException(e);
    } finally {
      transactionManager.endTransaction();
    }
  }

  protected void performDaoWriteOperation(DAOWriteOperation operation)
      throws ServiceException {
    try {
      operation.write();
      transactionManager.commitTransaction();
    } catch (DAOException e) {
      e.printStackTrace();
      transactionManager.rollbackTransaction();
      throw new ServiceException(e);
    } finally {
      transactionManager.endTransaction();
    }
  }

}
