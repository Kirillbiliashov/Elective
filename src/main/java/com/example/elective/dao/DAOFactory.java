package com.example.elective.dao;

import com.example.elective.dao.interfaces.*;
import com.example.elective.dao.sql.mysql.MySqlDAOFactory;

public abstract class DAOFactory {

  public final static int MYSQL = 1;

  public abstract AccountDAO getAccountDAO();
  public abstract CourseDAO getCourseDAO();
  public abstract JournalDAO getJournalDAO();
  public abstract TopicDAO getTopicDAO();


  public static DAOFactory getFactory(int dbms) {
    if (dbms == 1) return new MySqlDAOFactory();
    return null;
  }

}
