package com.example.elective.dao.sql;

import com.example.elective.dao.DAOFactory;
import com.example.elective.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

/**
 * Abstract base class for SQL factories with static method that retrieves connection to DB
 * @author Kirill Biliashov
 */

public abstract class SQLDAOFactory extends DAOFactory {

  private static final Logger logger = LogManager.getLogger(SQLDAOFactory.class);

  private static SessionFactory sessionFactory;

  public static synchronized Session getSession() {
    if (sessionFactory == null) configureDataSource();
    return sessionFactory.getCurrentSession();
  }

  private static void configureDataSource() {
    Configuration conf = new Configuration()
        .addAnnotatedClass(Account.class)
        .addAnnotatedClass(Blocklist.class)
        .addAnnotatedClass(Course.class)
        .addAnnotatedClass(Journal.class)
        .addAnnotatedClass(Topic.class);
    sessionFactory = conf.buildSessionFactory();
  }

}
