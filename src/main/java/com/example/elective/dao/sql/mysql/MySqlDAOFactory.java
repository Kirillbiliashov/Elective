package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.*;
import com.example.elective.dao.sql.SqlDAOFactory;

public class MySqlDAOFactory extends SqlDAOFactory {

  @Override
  public AccountDAO getAccountDAO() {
    return new AccountMySqlDAO();
  }

  @Override
  public CourseDAO getCourseDAO() {
    return new CourseMySqlDAO();
  }

  @Override
  public JournalDAO getJournalDAO() {
    return new JournalMySqlDAO();
  }

  @Override
  public TopicDAO getTopicDAO() {
    return new TopicMysqlDAO();
  }

}
