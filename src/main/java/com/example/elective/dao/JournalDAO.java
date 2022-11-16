package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {

  public static void save(Journal journal) {
    final String sqlStr = "INSERT INTO journal(enrollment_date, course_id," +
        " student_id) VALUES(?, ?, ?)";
    try (Connection conn = ConnectionPool.getConnection();
    PreparedStatement ps = conn.prepareStatement(sqlStr, PreparedStatement.RETURN_GENERATED_KEYS)) {
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


  public static List<Journal> getByCourseId(int courseId) {
    final String sqlStr = "SELECT * FROM journal WHERE course_id = ?";
    try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlStr)) {
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

  private static Journal mapResultSetToJournal(ResultSet rs) throws SQLException {
    return new Journal()
        .setId(rs.getInt("id"))
        .setGrade(rs.getInt("grade"))
        .setEnrollmentDate(rs.getDate("enrollment_date"))
        .setCourseId(rs.getInt("course_id"))
        .setStudentId(rs.getInt("student_id"));
  }
}
