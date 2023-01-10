package com.example.elective.services;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Topic;

import java.util.List;

/**
 * Class containing business logic methods regarding topics
 * @author Kirill Biliashov
 */

public class TopicService extends AbstractService {

  private final TopicDAO dao = daoFactory.getTopicDAO();

  public List<Topic> getAll() throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(dao::getAll);
  }

}
