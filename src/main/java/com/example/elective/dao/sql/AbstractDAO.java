package com.example.elective.dao.sql;

import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;
import com.example.elective.models.Entity;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDAO {

  protected Logger logger = LogManager.getLogger(AbstractDAO.class);

  protected Connection conn;

  public void setConnection(Connection conn) {
    this.conn = conn;
  }

}
