package com.example.elective.dao;

import com.example.elective.models.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Entity> implements DAO<T> {

  protected Connection conn;

  public void setConnection(Connection conn) {
    this.conn = conn;
  }

}
