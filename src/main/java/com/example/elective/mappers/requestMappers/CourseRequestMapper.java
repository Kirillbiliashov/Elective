package com.example.elective.mappers.requestMappers;

import com.example.elective.models.Course;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class CourseRequestMapper implements RequestMapper<Course> {

  @Override
  public Course map(HttpServletRequest req) {
    String idStr = req.getParameter("id");
    return Course.newBuilder()
        .setId(idStr == null ? 0 : Integer.parseInt(idStr))
        .setName(req.getParameter("name"))
        .setDescription(req.getParameter("description"))
        .setStartDate(Date.valueOf(req.getParameter("startDate")))
        .setEndDate(Date.valueOf(req.getParameter("endDate")))
        .setTopicId(Integer.parseInt(req.getParameter("topicId")))
        .setTeacherId(Integer.parseInt(req.getParameter("teacherId")))
        .build();
  }

}
