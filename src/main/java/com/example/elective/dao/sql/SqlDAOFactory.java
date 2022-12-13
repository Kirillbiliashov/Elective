package com.example.elective.dao.sql;

import com.example.elective.dao.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class SqlDAOFactory extends DAOFactory {

  private static final Logger logger = LogManager.getLogger(SqlDAOFactory.class);

  private static DataSource ds;

  public static Connection getConnection() throws SQLException {
    if (ds == null) configureDataSource();
    return ds.getConnection();
  }

  private static void configureDataSource() {
    try {
      Context initContext = new InitialContext();
      Context envContext  = (Context) initContext.lookup("java:/comp/env");
      ds = (DataSource) envContext.lookup("jdbc/elective");
    } catch (NamingException e) {
      logger.error("failed to retrieve data source: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
