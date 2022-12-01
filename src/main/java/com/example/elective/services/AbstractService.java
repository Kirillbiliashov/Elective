package com.example.elective.services;

import com.example.elective.TransactionManager;

public abstract class AbstractService {

  protected TransactionManager transactionManager = new TransactionManager();

  public void performWriteOperation(Runnable func) {
    func.run();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
  }

}
