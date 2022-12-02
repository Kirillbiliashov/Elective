package com.example.elective.services;

import com.example.elective.connection.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;

import java.util.function.Supplier;

public abstract class AbstractService {

  protected TransactionManager transactionManager = new TransactionManager();

  protected <T> T performDaoReadOperation(DAOReadOperation<T> operation)
      throws ServiceException {
    try {
      T res = operation.read();
      transactionManager.commitTransaction();
      return res;
    } catch (DAOException e) {
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
