package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Topic;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.TopicService;

import java.util.List;
import java.util.Optional;

/**
 * Class containing business logic methods regarding topics
 * @author Kirill Biliashov
 */

public class TopicServiceImpl extends AbstractService implements TopicService {

  @Override
  public List<Topic> getAll() throws ServiceException {
    TopicDAO dao = daoFactory.getTopicDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, dao::getAll);
  }

  @Override
  public Optional<Topic> find(TransactionManager tm, int id)
      throws DAOException {
    TopicDAO dao = daoFactory.getTopicDAO();
    tm.initTransaction(dao);
    return dao.find(id);
  }

}
