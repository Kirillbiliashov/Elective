package com.example.elective.services;

import com.example.elective.connection.TransactionManager;

import java.util.function.Supplier;

public abstract class AbstractService {

  protected TransactionManager transactionManager = new TransactionManager();

  public <T> T performReadOperation(Supplier<T> supplier) {
    T res = supplier.get();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return res;
  }

  public void performWriteOperation(Runnable func) {
    func.run();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
  }

}
