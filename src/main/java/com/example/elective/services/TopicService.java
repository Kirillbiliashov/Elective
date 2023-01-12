package com.example.elective.services;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Topic;

import java.util.List;
import java.util.Optional;

/**
 * Class containing business logic methods regarding topics
 * @author Kirill Biliashov
 */

public class TopicService extends AbstractService {

  public List<Topic> getAll() throws ServiceException {
    final TopicDAO dao = daoFactory.getTopicDAO();
    final TransactionManager tm = new TransactionManager();
    tm.initTransaction(dao);
    return read(tm, dao::getAll);
  }

  protected Optional<Topic> find(TransactionManager tm, int id)
      throws DAOException {
    final TopicDAO dao = daoFactory.getTopicDAO();
    tm.initTransaction(dao);
    return dao.find(id);
  }

}
