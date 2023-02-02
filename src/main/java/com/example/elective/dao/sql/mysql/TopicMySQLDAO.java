package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dao.sql.AbstractDAO;
import com.example.elective.models.Topic;

import java.util.List;
import java.util.Optional;

/**
 * Class that implements methods of TopicDAO for MySQL database
 * @author Kirill Biliashov
 */

public class TopicMySQLDAO extends AbstractDAO implements TopicDAO {

  private static final String FIND_ALL = "SELECT t FROM Topic t";

  @Override
  public List<Topic> getAll() {
    return session
        .createQuery(FIND_ALL, Topic.class)
        .getResultList();
  }

  @Override
  public Optional<Topic> find(int id) {
    return Optional.ofNullable(session.get(Topic.class, id));
  }

  @Override
  public void save(Topic entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(Topic entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(int id) {
    throw new UnsupportedOperationException();
  }

}
