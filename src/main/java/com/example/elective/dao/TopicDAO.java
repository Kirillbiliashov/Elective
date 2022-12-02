package com.example.elective.dao;

import com.example.elective.connection.ConnectionPool;
import com.example.elective.exceptions.DAOException;
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
  private Mapper<ResultSet, Topic> mapper = new TopicResultSetMapper();

  @Override
  public List<Topic> findAll() throws DAOException {
    try (Connection conn = ConnectionPool.getConnection();
    Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(FIND_ALL);
      List<Topic> topics = new ArrayList<>();
      while (rs.next()) topics.add(mapper.map(rs));
      return topics;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DAOException("unable to find topics", e);
    }
  }

  @Override
  public Optional<Topic> find(int id) throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void save(Topic entity) throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(Topic entity) throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(int id) throws DAOException {
    throw new UnsupportedOperationException();
  }

}
