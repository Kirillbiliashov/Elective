package com.example.elective.dao;

import com.example.elective.connection.ConnectionPool;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.resultSetMappers.TopicResultSetMapper;
import com.example.elective.models.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicDAO extends AbstractDAO<Topic> {

  private static final String FIND_ALL = "SELECT * FROM topic";

  public TopicDAO() {
    this.mapper = new TopicResultSetMapper();
  }

  @Override
  public List<Topic> findAll() throws DAOException {
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(FIND_ALL));
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
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
