package com.example.elective.dao;

import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.resultSetMappers.CourseResultSetMapper;
import com.example.elective.models.Course;

import java.sql.*;
import java.util.ArrayList;
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

  private Mapper<ResultSet, Course> mapper = new CourseResultSetMapper();

  public List<Course> getByTeacherId(int teacherId) {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_TEACHER_ID)) {
      ps.setInt(1, teacherId);
      ResultSet rs = ps.executeQuery();
      List<Course> courses = new ArrayList<>();
      while (rs.next()) courses.add(mapper.map(rs));
      return courses;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public Optional<Course> getByTeacherIdAtPosition(int teacherId, int position) {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_TEACHER_ID_AT_POS)) {
      ps.setInt(1, teacherId);
      ps.setInt(2, position - 1);
      ResultSet rs = ps.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(mapper.map(rs));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public void update(Course course) {
    try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      int idx = 1;
      ps.setString(idx++, course.getName());
      ps.setDate(idx++, course.getStartDate());
      ps.setDate(idx++, course.getEndDate());
      ps.setInt(idx++, course.getTopicId());
      ps.setInt(idx++, course.getTeacherId());
      ps.setInt(idx, course.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public void save(Course course) {
    try (PreparedStatement ps = conn.prepareStatement(SAVE,
        Statement.RETURN_GENERATED_KEYS)) {
      int idx = 1;
      ps.setString(idx++, course.getName());
      ps.setDate(idx++, course.getStartDate());
      ps.setDate(idx++, course.getEndDate());
      ps.setInt(idx++, course.getTopicId());
      int teacherId = course.getTeacherId();
      if (teacherId == 0) ps.setNull(idx, Types.INTEGER);
      else ps.setInt(idx, course.getTeacherId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) course.getBuilder().setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public void delete(int id) {
    try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public List<Course> findAll() {
    try (Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(GET_ALL);
      List<Course> courses = new ArrayList<>();
      while (rs.next()) courses.add(mapper.map(rs));
      return courses;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public Optional<Course> find(int id) {
    try (PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(mapper.map(rs));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

}
