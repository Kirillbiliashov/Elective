package com.example.elective.mappers.requestMappers;

import com.example.elective.models.Course;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Class that maps HttpServletRequest to Course
 * @author Kirill Biliashov
 */

public class CourseRequestMapper implements RequestMapper<Course> {

  private static final String ID = "id";
  private static final String NAME = "name";
  private static final String DESCRIPTION = "description";
  private static final String START_DATE = "startDate";
  private static final String END_DATE = "endDate";
  private static final String TOPIC_ID = "topicId";
  private static final String TEACHER_ID = "teacherId";

  @Override
  public Course map(HttpServletRequest req) {
    String idStr = req.getParameter(ID);
    return new Course()
        .setId(idStr == null ? 0 : Integer.parseInt(idStr))
        .setName(req.getParameter(NAME))
        .setDescription(req.getParameter(DESCRIPTION))
        .setStartDate(Date.valueOf(req.getParameter(START_DATE)))
        .setEndDate(Date.valueOf(req.getParameter(END_DATE)));
  }

}
