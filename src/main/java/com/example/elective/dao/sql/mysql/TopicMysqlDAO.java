package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.TopicResultSetMapper;
import com.example.elective.models.Topic;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TopicMysqlDAO extends MySqlDAO<Topic> implements TopicDAO {

  private static final String FIND_ALL = "SELECT * FROM topic";

  public TopicMysqlDAO() {
    this.mapper = new TopicResultSetMapper();
  }

  @Override
  public List<Topic> findAll() throws DAOException {
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(FIND_ALL));
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find topics", e);
    }
  }

  @Override
  public Optional<Topic> find(int id) {
    throw new UnsupportedOperationException();
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
