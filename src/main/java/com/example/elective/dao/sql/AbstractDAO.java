package com.example.elective.dao.sql;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract base class for DAOs containing logger and method for setting connection
 * @author Kirill Biliashov
 */

public abstract class AbstractDAO {

  protected Logger logger = LogManager.getLogger(AbstractDAO.class);

  protected Connection conn;

  public void setConnection(Connection conn) {
    this.conn = conn;
  }

}
