package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.CourseResultSetMapper;
import com.example.elective.models.Course;
import com.example.elective.selection.Pagination;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CourseMySqlDAO extends MySqlDAO<Course> implements CourseDAO {

  private static final String GET_ALL = "SELECT * FROM course";
  private static final String GET_BY_TEACHER_ID = GET_ALL +
      " WHERE teacher_id = ?";
  private static final String FIND_BY_TEACHER_ID_AT_POS = GET_BY_TEACHER_ID + " LIMIT ?,?";
  private static final String UPDATE = "UPDATE course SET name = ?," +
      " description = ?, start_date = ?, end_date = ?, topic_id = ?," +
      " teacher_id = ? WHERE id = ?";
  private static final String SAVE = "INSERT INTO course(name, description," +
      " start_date,end_date, topic_id, teacher_id) VALUES(?,?,?,?,?,?)";
  private static final String DELETE = "DELETE FROM course WHERE id = ?";
  private static final String FIND_BY_ID = GET_ALL + " WHERE id = ?";
  private static final String SELECT_JOIN_JOURNAL = "SELECT course.id," +
      " name, description, start_date, end_date, topic_id, teacher_id " +
      "FROM course JOIN journal ON course.id = course_id ";
  private static final String GET_COMPLETED_FOR_STUDENT = SELECT_JOIN_JOURNAL +
      "WHERE student_id = ? AND end_date < CURRENT_DATE()";
  private static final String GET_IN_PROGRESS_FOR_STUDENT = SELECT_JOIN_JOURNAL +
      "WHERE student_id = ? AND CURRENT_DATE() BETWEEN start_date AND end_date";
  private static final String GET_REGISTERED_FOR_STUDENT = SELECT_JOIN_JOURNAL +
      "WHERE student_id = ? AND start_date > CURRENT_DATE()";
  private static final String GET_AVAILABLE_FOR_STUDENT = GET_ALL +
      " WHERE id != ALL (SELECT course.id FROM course " +
      "JOIN journal ON course_id = course.id WHERE student_id = ?)" +
      " AND start_date > CURRENT_DATE()";
  private static final String GET_COUNT_BY_TEACHER = "SELECT COUNT(*) " +
      "FROM course WHERE teacher_id = ?";

  public CourseMySqlDAO() {
    this.mapper = new CourseResultSetMapper();
  }

  @Override
  public Optional<Course> findByTeacherId(int teacherId, Pagination pagination)
      throws DAOException {
    try {
      return getOptionalEntity(FIND_BY_TEACHER_ID_AT_POS, teacherId,
          pagination.getFrom(), pagination.getDisplayCount());
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find teacher's course", e);
    }
  }

  @Override
  public void update(Course course) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      addValuesToPreparedStatement(ps, course.getName(),
          course.getDescription(), course.getStartDate(),
          course.getEndDate(), course.getTopicId(),
          course.getTeacherId(), course.getId());
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
      addValuesToPreparedStatement(ps, course.getName(),
          course.getDescription(), course.getStartDate(),
          course.getEndDate(), course.getTopicId(), course.getTeacherId());
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
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to delete course", e);
    }
  }

  @Override
  public List<Course> getAll() throws DAOException {
    try {
      return getEntitiesList(GET_ALL);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find courses", e);
    }
  }

  @Override
  public Optional<Course> find(int id) throws DAOException {
    try {
      return getOptionalEntity(FIND_BY_ID, id);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find course", e);
    }
  }

  @Override
  public List<Course> getCompletedForStudent(int studentId) throws DAOException {
    return getByStudentId(studentId, GET_COMPLETED_FOR_STUDENT);
  }

  @Override
  public List<Course> getInProgressForStudent(int studentId)
      throws DAOException {
    return getByStudentId(studentId, GET_IN_PROGRESS_FOR_STUDENT);
  }

  @Override
  public List<Course> getRegisteredForStudent(int studentId)
      throws DAOException {
    return getByStudentId(studentId, GET_REGISTERED_FOR_STUDENT);
  }

  @Override
  public List<Course> getAvailableForStudent(int studentId)
      throws DAOException {
    return getByStudentId(studentId, GET_AVAILABLE_FOR_STUDENT);
  }

  @Override
  public int getCount(int teacherId) throws DAOException {
    try {
      return getCount(GET_COUNT_BY_TEACHER, teacherId);
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to get courses count", e);
    }
  }

  private List<Course> getByStudentId(int studentId, String query)
      throws DAOException {
    try {
      return getEntitiesList(query, studentId);
    } catch (SQLException | MappingException e) {
      logger.error(e.getMessage());
      throw new DAOException("unable to find courses", e);
    }
  }

}
