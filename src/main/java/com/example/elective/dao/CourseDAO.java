package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDAO {

  public static List<Course> getByTeacherId(int teacherId) {
    final String sqlStr = "SELECT * FROM course WHERE teacher_id = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setInt(1, teacherId);
      ResultSet rs = ps.executeQuery();
      List<Course> courses = new ArrayList<>();
      while (rs.next()) courses.add(mapResultSetToCourse(rs));
      return courses;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static void update(Course course) {
    final String sqlStr = "UPDATE course SET name = ?, duration = ?," +
        " start_date = ?, topic_id = ?, teacher_id = ? WHERE id = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      int idx = 1;
      ps.setString(idx++, course.getName());
      ps.setInt(idx++, course.getDuration());
      ps.setDate(idx++, course.getStartDate());
      ps.setInt(idx++, course.getTopicId());
      ps.setInt(idx++, course.getTeacherId());
      ps.setInt(idx, course.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static void save(Course course) {
    final String sqlStr = "INSERT INTO course(name, duration, start_date, topic_id, teacher_id) VALUES(?,?,?,?,?)";
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS)) {
      int idx = 1;
      ps.setString(idx++, course.getName());
      ps.setInt(idx++, course.getDuration());
      ps.setDate(idx++, course.getStartDate());
      ps.setInt(idx++, course.getTopicId());
      ps.setInt(idx, course.getTeacherId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) course.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static void delete(int id) {
    final String sqlStr = "DELETE FROM course WHERE id = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static List<Course> getAll() {
    final String sqlStr = "SELECT * FROM course";
    try (Connection conn = ConnectionPool.getConnection();
         Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(sqlStr);
      List<Course> courses = new ArrayList<>();
      while (rs.next()) courses.add(mapResultSetToCourse(rs));
      return courses;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static Optional<Course> getById(int id) {
    final String sqlStr = "SELECT * FROM course WHERE id = ?";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr)) {
      ps.setInt(1, id);
      return mapResultSetToOptionalCourse(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static Optional<Course> mapResultSetToOptionalCourse(ResultSet rs)
      throws SQLException {
    if (!rs.next()) return Optional.empty();
    return Optional.of(mapResultSetToCourse(rs));
  }

  private static Course mapResultSetToCourse(ResultSet rs) throws SQLException {
    return new Course()
        .setId(rs.getInt("id"))
        .setName(rs.getString("name"))
        .setDuration(rs.getInt("duration"))
        .setStartDate(rs.getDate("start_date"))
        .setTeacherId(rs.getInt("teacher_id"))
        .setTopicId(rs.getInt("topic_id"));
  }
}
