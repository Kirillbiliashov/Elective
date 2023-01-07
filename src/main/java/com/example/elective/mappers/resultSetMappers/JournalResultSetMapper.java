package com.example.elective.mappers.resultSetMappers;

import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Journal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalResultSetMapper extends ResultSetMapper<Journal> {

  private static final String GRADE_COL_NAME = "grade";
  private static final String ENROLLMENT_DATE_COL_NAME = "enrollment_date";
  private static final String COURSE_ID_COL_NAME = "course_id";
  private static final String STUDENT_ID_COL_NAME = "student_id";

  @Override
  public Journal map(ResultSet rs) throws MappingException {
    try {
      return Journal.newBuilder()
          .setId(rs.getInt(ID_COL_NAME))
          .setGrade(rs.getInt(GRADE_COL_NAME))
          .setEnrollmentDate(rs.getDate(ENROLLMENT_DATE_COL_NAME))
          .setCourseId(rs.getInt(COURSE_ID_COL_NAME))
          .setStudentId(rs.getInt(STUDENT_ID_COL_NAME))
          .build();
    } catch (SQLException e) {
      throw new MappingException(e);
    }
  }

}
