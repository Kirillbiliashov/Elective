package com.example.elective.services;

import com.example.elective.TransactionManager;
import com.example.elective.dao.TopicDAO;
import com.example.elective.models.Topic;

import java.util.List;

public class TopicService {

  private TopicDAO dao = new TopicDAO();
  private TransactionManager transactionManager = new TransactionManager();

  public List<Topic> getAll() {
    transactionManager.initTransaction(dao);
    List<Topic> topics = dao.findAll();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return topics;
  }

}
