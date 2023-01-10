package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.TopicResultSetMapper;
import com.example.elective.models.Topic;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Class that implements methods of TopicDAO for MySQL database
 * @author Kirill Biliashov
 */

public class TopicMySQLDAO extends MySQLDAO<Topic> implements TopicDAO {

  private static final String FIND_ALL = "SELECT * FROM topic";
  private static final String FIND = "SELECT * FROM topic WHERE id = ?";

  public TopicMySQLDAO() {
    this.mapper = new TopicResultSetMapper();
  }

  @Override
  public List<Topic> getAll() throws DAOException {
    try {
      return getEntitiesList(FIND_ALL);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find topics", e);
    }
  }

  @Override
  public Optional<Topic> find(int id) throws DAOException {
    try {
      return getOptionalEntity(FIND, id);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find topic", e);
    }
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
