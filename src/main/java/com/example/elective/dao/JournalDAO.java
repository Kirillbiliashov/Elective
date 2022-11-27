package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JournalDAO {

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


  public void update(Journal journal) {
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(UPDATE)) {
      int idx = 1;
      ps.setInt(idx++, journal.getGrade());
      ps.setDate(idx++, journal.getEnrollmentDate());
      ps.setInt(idx, journal.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public Optional<Journal> getById(int journalId) {
    try(Connection conn = ConnectionPool.getConnection();
        PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {
      ps.setInt(1, journalId);
      return mapResultSetToOptionalJournal(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public Optional<Journal> findByCourseAndStudent(int courseId, int studentId) {
    try(Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(FIND_BY_COURSE_AND_STUDENT)) {
      int idx = 1;
      ps.setInt(idx++, courseId);
      ps.setInt(idx, studentId);
      return mapResultSetToOptionalJournal(ps.executeQuery());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public void save(Journal journal) {
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(SAVE,
             PreparedStatement.RETURN_GENERATED_KEYS)) {
      int idx = 1;
      ps.setDate(idx++, journal.getEnrollmentDate());
      ps.setInt(idx++, journal.getCourseId());
      ps.setInt(idx, journal.getStudentId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) journal.setId(rs.getInt(1));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }


  public List<Journal> getByCourseId(int courseId) {
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(GET_BY_COURSE_ID)) {
      ps.setInt(1, courseId);
      ResultSet rs = ps.executeQuery();
      List<Journal> journalList = new ArrayList<>();
      while (rs.next()) journalList.add(mapResultSetToJournal(rs));
      return journalList;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private Optional<Journal> mapResultSetToOptionalJournal(ResultSet rs)
      throws SQLException {
    if (!rs.next()) return Optional.empty();
    return Optional.of(mapResultSetToJournal(rs));
  }

  private Journal mapResultSetToJournal(ResultSet rs) throws SQLException {
    return new Journal()
        .setId(rs.getInt("id"))
        .setGrade(rs.getInt("grade"))
        .setEnrollmentDate(rs.getDate("enrollment_date"))
        .setCourseId(rs.getInt("course_id"))
        .setStudentId(rs.getInt("student_id"));
  }

}
