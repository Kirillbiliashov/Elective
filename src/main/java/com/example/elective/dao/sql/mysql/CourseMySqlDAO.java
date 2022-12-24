package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.CourseResultSetMapper;
import com.example.elective.models.Course;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CourseMySqlDAO extends MySqlDAO<Course> implements CourseDAO {

  private static final String GET_BY_TEACHER_ID = "SELECT * FROM course" +
      " WHERE teacher_id = ?";
  private static final String GET_BY_TEACHER_ID_AT_POS = GET_BY_TEACHER_ID + " LIMIT ?,1";
  private static final String UPDATE = "UPDATE course SET name = ?," +
      "start_date = ?, end_date = ?, topic_id = ?, teacher_id = ? " +
      "WHERE id = ?";
  private static final String SAVE = "INSERT INTO course(name, start_date," +
      " end_date, topic_id, teacher_id) VALUES(?,?,?,?,?)";
  private static final String DELETE = "DELETE FROM course WHERE id = ?";
  private static final String GET_ALL = "SELECT * FROM course";
  private static final String GET_BY_ID = "SELECT * FROM course WHERE id = ?";
  private static final String GET_ORDERED_BY = "SELECT * FROM course ORDER BY ";
  private static final String SELECT_JOIN_JOURNAL = "SELECT course.id," +
      " name, start_date, end_date, topic_id, teacher_id " +
      "FROM course JOIN journal ON course.id = course_id ";
  private static final String GET_ORDERED_BY_STUDENT_COUNT = SELECT_JOIN_JOURNAL +
      "GROUP BY (course.id) ORDER BY COUNT(course.id)";
  private static final String FIND_COMPLETED_FOR_STUDENT = SELECT_JOIN_JOURNAL +
      "WHERE student_id = ? AND end_date < CURRENT_DATE()";
  private static final String FIND_IN_PROGRESS_FOR_STUDENT = SELECT_JOIN_JOURNAL +
      "WHERE student_id = ? AND CURRENT_DATE() BETWEEN start_date AND end_date";
  private static final String FIND_REGISTERED_FOR_STUDENT = SELECT_JOIN_JOURNAL +
      "WHERE student_id = ? AND start_date > CURRENT_DATE()";
  private static final String FIND_AVAILABLE_FOR_STUDENT = "SELECT" +
      " * FROM course WHERE id != ALL (SELECT course.id FROM course " +
      "JOIN journal ON course_id = course.id WHERE student_id = ?)";


  public CourseMySqlDAO() {
    this.mapper = new CourseResultSetMapper();
  }

  public List<Course> getAllOrderedByStudentCount(boolean isAsc) throws DAOException {
    String ascStr = isAsc ? "" : " DESC";
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(GET_ORDERED_BY_STUDENT_COUNT + ascStr));
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to retrieve courses", e);
    }
  }

  public List<Course> getOrderedBy(String orderBy) throws DAOException {
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(GET_ORDERED_BY + orderBy));
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to retrieve courses", e);
    }
  }

  public List<Course> getByTeacherId(int teacherId) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_TEACHER_ID)) {
      addValuesToPreparedStatement(ps, teacherId);
      return getEntitiesList(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find teacher courses", e);
    }
  }

  @Override
  public Optional<Course> getByTeacherIdAtPosition(int teacherId, int position)
      throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_TEACHER_ID_AT_POS)) {
      addValuesToPreparedStatement(ps, teacherId, position - 1);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find teacher's course", e);
    }
  }

  @Override
  public void update(Course course) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      addValuesToPreparedStatement(ps, course.getName(),
          course.getStartDate(), course.getEndDate(),
          course.getTopicId(), course.getTeacherId(), course.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to update course", e);
    }
  }

  @Override
  public void save(Course course) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(SAVE,
        Statement.RETURN_GENERATED_KEYS)) {
      int teacherId = course.getTeacherId();
      addValuesToPreparedStatement(ps, course.getName(),
          course.getStartDate(), course.getEndDate(), course.getTopicId(),
          teacherId == 0 ? null : teacherId);
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) course.getBuilder().setId(rs.getInt(1));
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to save course", e);
    }
  }

  @Override
  public void delete(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
      addValuesToPreparedStatement(ps, id);
      ps.executeUpdate();
    } catch(SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to delete course", e);
    }
  }

  @Override
  public List<Course> findAll() throws DAOException {
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(GET_ALL));
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find courses", e);
    }
  }

  @Override
  public Optional<Course> find(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      addValuesToPreparedStatement(ps, id);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find course", e);
    }
  }

  @Override
  public List<Course> findCompletedForStudent(int studentId) throws DAOException {
    return findByStudentId(studentId, FIND_COMPLETED_FOR_STUDENT);
  }

  @Override
  public List<Course> findInProgressForStudent(int studentId)
      throws DAOException {
    return findByStudentId(studentId, FIND_IN_PROGRESS_FOR_STUDENT);
  }

  @Override
  public List<Course> findRegisteredForStudent(int studentId)
      throws DAOException {
    return findByStudentId(studentId, FIND_REGISTERED_FOR_STUDENT);
  }

  @Override
  public List<Course> findAvailableForStudent(int studentId)
      throws DAOException {
    return findByStudentId(studentId, FIND_AVAILABLE_FOR_STUDENT);
  }

  private List<Course> findByStudentId(int studentId, String query) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(query)) {
      addValuesToPreparedStatement(ps, studentId);
      return getEntitiesList(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find courses", e);
    }
  }

}
