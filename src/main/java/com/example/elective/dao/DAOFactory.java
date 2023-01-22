package com.example.elective.dao;

import com.example.elective.dao.interfaces.*;
import com.example.elective.dao.sql.mysql.MySQLDAOFactory;

/**
 * Class with abstract methods that return DAOs and static method that
 * returns specific DAO factory based on input parameter
 * @author Kirill Biliashov
 */

public abstract class DAOFactory {

  public final static int MYSQL = 1;

  public static DAOFactory getFactory(int dbms) {
    if (dbms == 1) return new MySQLDAOFactory();
    return null;
  }

  public abstract AccountDAO getAccountDAO();

  public abstract CourseDAO getCourseDAO();

  public abstract JournalDAO getJournalDAO();

  public abstract TopicDAO getTopicDAO();
  public abstract BlocklistDAO getBlocklistDAO();

}
