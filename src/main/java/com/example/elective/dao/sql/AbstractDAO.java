package com.example.elective.dao.sql;

import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;
import com.example.elective.models.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO {

  protected Connection conn;

  public void setConnection(Connection conn) {
    this.conn = conn;
  }

}
