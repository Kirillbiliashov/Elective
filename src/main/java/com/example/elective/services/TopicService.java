package com.example.elective.services;

import com.example.elective.dao.TopicDAO;
import com.example.elective.dao.mysql.TopicMysqlDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Topic;

import java.util.List;

public class TopicService extends AbstractService {

  private TopicDAO dao = daoFactory.getTopicDAO();

  public List<Topic> getAll() throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.findAll());
  }

}
