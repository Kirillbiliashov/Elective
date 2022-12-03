package com.example.elective.dao;

import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.resultSetMappers.CourseResultSetMapper;
import com.example.elective.models.Course;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CourseDAO extends AbstractDAO<Course> {

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
  private static final String GET_ORDERED_BY_STUDENT_COUNT = "SELECT course.id," +
      " course.name, course.start_date, course.end_date, course.topic_id," +
      " course.teacher_id FROM course JOIN journal ON" +
      " course.id = journal.course_id " +
      "GROUP BY (course.id) ORDER BY COUNT(course.id)";

  public CourseDAO() {
    this.mapper = new CourseResultSetMapper();
  }

  public List<Course> getAllOrderedByStudentCount(boolean isAsc) throws DAOException {
    String ascStr = isAsc ? "" : " DESC";
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(GET_ORDERED_BY_STUDENT_COUNT + ascStr));
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
      throw new DAOException("unable to retrieve courses", e);
    }
  }

  public List<Course> getOrderedBy(String orderBy) throws DAOException {
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(GET_ORDERED_BY + orderBy));
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
      throw new DAOException("unable to retrieve courses", e);
    }
  }

  public List<Course> getByTeacherId(int teacherId) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_TEACHER_ID)) {
      addValuesToPreparedStatement(ps, teacherId);
      return getEntitiesList(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
      throw new DAOException("unable to find teacher courses", e);
    }
  }

  public Optional<Course> getByTeacherIdAtPosition(int teacherId, int position)
      throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_TEACHER_ID_AT_POS)) {
      addValuesToPreparedStatement(ps, teacherId, position - 1);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
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
      e.printStackTrace();
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
      e.printStackTrace();
      throw new DAOException("unable to save course", e);
    }
  }

  @Override
  public void delete(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
      addValuesToPreparedStatement(ps, id);
      ps.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
      throw new DAOException("unable to delete course", e);
    }
  }

  @Override
  public List<Course> findAll() throws DAOException {
    try (Statement stmt = conn.createStatement()) {
      return getEntitiesList(stmt.executeQuery(GET_ALL));
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
      throw new DAOException("unable to find courses", e);
    }
  }

  @Override
  public Optional<Course> find(int id) throws DAOException {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      addValuesToPreparedStatement(ps, id);
      return getOptionalEntity(ps.executeQuery());
    } catch (SQLException | MappingException e) {
      e.printStackTrace();
      throw new DAOException("unable to find course", e);
    }
  }

}
//SELECT course.id FROM course JOIN journal ON course.id = journal.course_id GROUP BY (course.id) ORDER BY COUNT(course.id);