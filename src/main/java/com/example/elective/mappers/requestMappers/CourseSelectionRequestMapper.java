package com.example.elective.mappers.requestMappers;

import com.example.elective.selection.CourseSelection;

import javax.servlet.http.HttpServletRequest;

/**
 * Class that maps HttpServletRequest to CourseSelection
 * @author Kirill Biliashov
 */

public class CourseSelectionRequestMapper implements RequestMapper<CourseSelection> {

  private static final String TEACHER = "teacher";
  private static final String TOPIC = "topic";
  private static final String SORT = "sort";

  @Override
  public CourseSelection map(HttpServletRequest req) {
    String teacher = req.getParameter(TEACHER);
    String topic = req.getParameter(TOPIC);
    String sort = req.getParameter(SORT);
    return new CourseSelection(teacher, topic, sort);
  }

}