package com.example.elective.services;

import com.example.elective.connection.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;

import java.util.function.Supplier;

public abstract class AbstractService {

  protected TransactionManager transactionManager = new TransactionManager();

  protected <T> T performDaoReadOperation(DAOReadOperation<T> operation) throws ServiceException {
    T res = null;
    try {
      res = operation.read();
    } catch (DAOException e) {
      e.printStackTrace();
      throw new ServiceException(e);
    }
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return res;
  }

  protected void performDaoWriteOperation(DAOWriteOperation operation) throws ServiceException {
    try {
      operation.write();
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
  }

}
