package com.example.elective.mappers.requestMappers;

import com.example.elective.CourseSelection;
import com.example.elective.utils.RegexUtils;

import javax.servlet.http.HttpServletRequest;

public class CourseSelectionRequestMapper implements RequestMapper<CourseSelection> {

  @Override
  public CourseSelection map(HttpServletRequest req) {
    String teacher = req.getParameter("teacher");
    String topic = req.getParameter("topic");
    String sort = req.getParameter("sort");
    return new CourseSelection(teacher, topic, sort);
  }

}