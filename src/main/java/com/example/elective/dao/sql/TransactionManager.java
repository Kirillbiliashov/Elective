package com.example.elective.dao.sql;

import com.example.elective.dao.interfaces.DAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class with methods responsible for starting, committing, ending, rolling back transactions
 * @author Kirill Biliashov
 */
public class TransactionManager {

  private final Logger logger = LogManager.getLogger(TransactionManager.class);

  private Connection conn;

  public void initTransaction(DAO... daos) {
    try {
      if (conn == null) conn = SQLDAOFactory.getConnection();
      conn.setAutoCommit(false);
      conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    } catch (SQLException e) {
      logger.error("failed to init transaction: " + e.getMessage());
    }
    for (DAO dao : daos) dao.setConnection(conn);
  }

  public void commitTransaction() {
    if (conn == null) return;
    try {
      conn.commit();
    } catch (SQLException e) {
      logger.error("failed to commit transaction: " + e.getMessage());
    }
  }

  public void rollbackTransaction() {
    if (conn == null) return;
    try {
      conn.rollback();
    } catch (SQLException e) {
      logger.error("failed to rollback transaction: " + e.getMessage());
    }
  }

  public void endTransaction() {
    if (conn == null) return;
    try {
      conn.setAutoCommit(true);
      conn.close();
    } catch (SQLException e) {
      logger.error("failed to end transaction: " + e.getMessage());
    }
    conn = null;
  }

}
