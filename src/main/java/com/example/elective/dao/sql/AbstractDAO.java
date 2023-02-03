package com.example.elective.dao.sql;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

/**
 * Abstract base class for DAOs containing logger and method for setting connection
 * @author Kirill Biliashov
 */

public abstract class AbstractDAO {

  protected Session session;

  public void setSession(Session session) {
    this.session = session;
  }

}
