package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.*;
import com.example.elective.dao.sql.SQLDAOFactory;

/**
 * Factory class that returns DAOs for MySQL
 * @author Kirill Biliashov
 */

public class MySQLDAOFactory extends SQLDAOFactory {

  @Override
  public AccountDAO getAccountDAO() {
    return new AccountMySQLDAO();
  }

  @Override
  public CourseDAO getCourseDAO() {
    return new CourseMySQLDAO();
  }

  @Override
  public JournalDAO getJournalDAO() {
    return new JournalMySQLDAO();
  }

  @Override
  public TopicDAO getTopicDAO() {
    return new TopicMySQLDAO();
  }

}
