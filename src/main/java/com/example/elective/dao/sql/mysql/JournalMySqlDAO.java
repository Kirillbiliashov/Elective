package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.JournalResultSetMapper;
import com.example.elective.models.Journal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JournalMySqlDAO extends MySqlDAO<Journal> implements JournalDAO {

  private static final String UPDATE = "UPDATE journal SET grade = ?," +
      " enrollment_date = ? WHERE id = ?";
  private static final String GET_BY_ID = "SELECT  * FROM journal" +
      " WHERE id = ?";
  private static final String FIND_BY_COURSE_AND_STUDENT = "SELECT  * FROM " +
      "journal WHERE course_id = ? AND student_id = ?";
  private static final String SAVE = "INSERT INTO journal(enrollment_date," +
      " course_id, student_id) VALUES(?, ?, ?)";
  private static final String GET_BY_COURSE_ID = "SELECT * FROM journal" +
      " WHERE course_id = ?";

  public JournalMySqlDAO() {
    this.mapper = new JournalResultSetMapper();
  }

  @Override
  public Optional<Journal> find(int journalId) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      addValuesToPreparedStatement(ps, journalId);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find journal entry", e);
    }
  }

  @Override
  public void update(Journal journal) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      addValuesToPreparedStatement(ps, journal.getGrade(),
          journal.getEnrollmentDate(), journal.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to update journal entry", e);
    }
  }

  @Override
  public void save(Journal journal) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(SAVE,
             PreparedStatement.RETURN_GENERATED_KEYS)) {
      addValuesToPreparedStatement(ps, journal.getEnrollmentDate(),
          journal.getCourseId(), journal.getStudentId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) journal.getBuilder().setId(rs.getInt(1));
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to save journal entry", e);
    }
  }

  @Override
  public void delete(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Journal> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Journal> findByCourseAndStudent(int courseId, int studentId)
      throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(FIND_BY_COURSE_AND_STUDENT)) {
      addValuesToPreparedStatement(ps, courseId, studentId);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find journal entry for given course and student", e);
    }
  }

  @Override
  public List<Journal> getByCourseId(int courseId) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_COURSE_ID)) {
      addValuesToPreparedStatement(ps, courseId);
      return getEntitiesList(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find journal entries for course", e);
    }
  }

}