package com.example.elective.mappers.resultSetMappers;

import com.example.elective.mappers.Mapper;
import com.example.elective.models.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class ResultSetMapper<T extends Entity> implements Mapper<ResultSet, T> {

  protected static final String ID_COL_NAME = "id";

}
