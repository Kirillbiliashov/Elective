package com.example.elective.mappers.resultSetMappers;

import com.example.elective.mappers.Mapper;
import com.example.elective.models.Entity;

import java.sql.ResultSet;

/**
 * Abstract base class that implements Mapper interface with ResultSet inputType parameter
 * and Entity output type parameter
 * @param <T> type parameter, subclass of Entity
 * @author Kirill Biliashov
 */

public abstract class ResultSetMapper<T extends Entity> implements Mapper<ResultSet, T> {

  protected static final String ID_COL_NAME = "id";

}
