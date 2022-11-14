package com.example.elective.dao;

import com.example.elective.ConnectionPool;
import com.example.elective.models.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {

  public static List<Topic> getAll() {
    final String sqlStr = "SELECT * FROM topic";
    try (Connection conn = ConnectionPool.getConnection();
    Statement stmt = conn.createStatement()) {
      ResultSet rs = stmt.executeQuery(sqlStr);
      List<Topic> topics = new ArrayList<>();
      while (rs.next()) topics.add(mapResultSetToTopic(rs));
      return topics;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private static Topic mapResultSetToTopic(ResultSet rs) throws SQLException {
    return new Topic().setId(rs.getInt("id"))
        .setName(rs.getString("name"));
  }
}
