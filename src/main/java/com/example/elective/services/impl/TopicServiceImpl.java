package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Topic;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.TopicService;
import org.hibernate.Session;

import java.util.List;

/**
 * Class containing business logic methods regarding topics
 * @author Kirill Biliashov
 */

public class TopicServiceImpl extends AbstractService implements TopicService {

  @Override
  public List<Topic> getAll() {
    TopicDAO dao = daoFactory.getTopicDAO();
    return read(dao::getAll, dao);
  }

}
