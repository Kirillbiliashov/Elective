package com.example.elective.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> implements DAO<T> {

  protected Connection conn;

  public void setConnection(Connection conn) {
    this.conn = conn;
  }

}
