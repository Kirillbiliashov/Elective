package com.example.elective;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

  private static DataSource ds;

  public static Connection getConnection() throws SQLException {
    if (ds == null) configureDataSource();
    return ds.getConnection();
  }

  private static void configureDataSource() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Context initContext = new InitialContext();
      Context envContext  = (Context) initContext.lookup("java:/comp/env");
      ds = (DataSource) envContext.lookup("jdbc/elective");
    } catch (NamingException | ClassNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
