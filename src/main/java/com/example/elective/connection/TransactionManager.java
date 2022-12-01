package com.example.elective.connection;

import com.example.elective.dao.AbstractDAO;
import com.example.elective.models.Entity;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

  private Connection conn;

  @SafeVarargs
  public final void initTransaction(AbstractDAO<? extends Entity>... daos)  {
    try {
      if (conn == null) conn = ConnectionPool.getConnection();
      conn.setAutoCommit(false);
      for (AbstractDAO<? extends Entity> dao: daos) dao.setConnection(conn);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public void commitTransaction() {
    if (conn == null) return;
    try {
      conn.commit();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public void rollbackTransaction() {
    if (conn == null) return;
    try {
      conn.rollback();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void endTransaction() {
    if (conn == null) return;
    try {
      conn.setAutoCommit(true);
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    conn = null;
  }

}
