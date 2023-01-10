package com.example.elective.mappers.resultSetMappers;

import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that maps ResultSet to Course
 * @author Kirill Biliashov
 */

public class CourseResultSetMapper extends ResultSetMapper<Course> {

  private static final String NAME_COL_NAME = "name";
  private static final String DESCRIPTION_COL_NAME = "description";
  private static final String START_DATE_COL_NAME = "start_date";
  private static final String END_DATE_COL_NAME = "end_date";
  private static final String TEACHER_ID_COL_NAME = "teacher_id";
  private static final String TOPIC_ID_COL_NAME = "topic_id";

  @Override
  public Course map(ResultSet rs) throws MappingException {
    try {
      return Course.newBuilder()
          .setId(rs.getInt(ID_COL_NAME))
          .setName(rs.getString(NAME_COL_NAME))
          .setDescription(rs.getString(DESCRIPTION_COL_NAME))
          .setStartDate(rs.getDate(START_DATE_COL_NAME))
          .setEndDate(rs.getDate(END_DATE_COL_NAME))
          .setTeacherId(rs.getInt(TEACHER_ID_COL_NAME))
          .setTopicId(rs.getInt(TOPIC_ID_COL_NAME))
          .build();
    } catch (SQLException e) {
      throw new MappingException(e);
    }
  }

}
