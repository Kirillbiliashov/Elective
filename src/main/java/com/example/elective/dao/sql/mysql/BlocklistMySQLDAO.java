package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.BlocklistResultSetMapper;
import com.example.elective.models.Blocklist;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BlocklistMySQLDAO extends MySQLDAO<Blocklist> implements BlocklistDAO {

  private static final String FIND_BY_STUDENT = "SELECT * FROM blocklist WHERE student_id = ?";
  private static final String SAVE = "INSERT INTO blocklist(student_id) VALUES(?)";
  private static final String DELETE = "DELETE FROM blocklist WHERE student_id = ?";

  public BlocklistMySQLDAO() {
    this.mapper = new BlocklistResultSetMapper();
  }

  @Override
  public List<Blocklist> getAll() throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Blocklist> find(int id) throws DAOException {
    try {
      return getOptionalEntity(FIND_BY_STUDENT, id);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to get blocklist entry", e);
    }
  }

  @Override
  public void save(Blocklist blocklist) throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(Blocklist entity) throws DAOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(int studentId) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
      addValuesToPreparedStatement(ps, studentId);
      ps.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to remove blocklist entry", e);
    }
  }

  @Override
  public void save(int studentId) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(SAVE)) {
      addValuesToPreparedStatement(ps, studentId);
      ps.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to save to block list", e);
    }
  }

}
