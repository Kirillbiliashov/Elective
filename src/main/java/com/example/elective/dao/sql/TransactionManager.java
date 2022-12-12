package com.example.elective.dao.sql;

import com.example.elective.dao.interfaces.DAO;
import com.example.elective.dao.sql.mysql.MySqlDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

  private Connection conn;

  public final void initTransaction(DAO... daos) {
    try {
      if (conn == null) conn = SqlDAOFactory.getConnection();
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    for (DAO dao: daos) dao.setConnection(conn);
  }

  public void commitTransaction() {
    if (conn == null) return;
    try {
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void rollbackTransaction() {
    if (conn == null) return;
    try {
      conn.rollback();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void endTransaction() {
    if (conn == null) return;
    try {
      conn.setAutoCommit(true);
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    conn = null;
  }

}
