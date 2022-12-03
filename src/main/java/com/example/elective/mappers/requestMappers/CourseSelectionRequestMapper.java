package com.example.elective.mappers.requestMappers;

import com.example.elective.CourseSelection;
import com.example.elective.Utils;

import javax.servlet.http.HttpServletRequest;

public class CourseSelectionRequestMapper implements RequestMapper<CourseSelection> {

  @Override
  public CourseSelection map(HttpServletRequest req) {
    String teacherIdStr = req.getParameter("teacher");
    String topicIdStr = req.getParameter("topic");
    String sort = req.getParameter("sort");
    boolean isTeacherNull = teacherIdStr == null;
    boolean isTopicNull = topicIdStr == null;
    boolean isSortNull = sort == null;
    if (isSortNull && isTopicNull && isTeacherNull) return null;
    int teacherId = Utils.isNumeric(teacherIdStr) ? Integer.parseInt(teacherIdStr) : 0;
    int topicId = Utils.isNumeric(topicIdStr) ? Integer.parseInt(topicIdStr) : 0;
    return new CourseSelection(teacherId, topicId, sort);
  }

}