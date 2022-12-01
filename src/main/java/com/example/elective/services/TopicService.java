package com.example.elective.services;

import com.example.elective.dao.TopicDAO;
import com.example.elective.models.Topic;

import java.util.List;

public class TopicService extends AbstractService {

  private TopicDAO dao = new TopicDAO();

  public List<Topic> getAll() {
    transactionManager.initTransaction(dao);
    return performReadOperation(() -> dao.findAll());
  }

}
