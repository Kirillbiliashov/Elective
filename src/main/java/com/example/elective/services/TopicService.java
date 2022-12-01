package com.example.elective.services;

import com.example.elective.TransactionManager;
import com.example.elective.dao.TopicDAO;
import com.example.elective.models.Topic;

import java.util.List;

public class TopicService extends AbstractService {

  private TopicDAO dao = new TopicDAO();

  public List<Topic> getAll() {
    transactionManager.initTransaction(dao);
    List<Topic> topics = dao.findAll();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return topics;
  }

}
