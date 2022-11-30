package com.example.elective.mappers.requestMappers;

import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class CourseRequestMapper implements RequestMapper<Course> {

  @Override
  public Course map(HttpServletRequest req) {
    return Course.newBuilder()
        .setName(req.getParameter("name"))
        .setStartDate(Date.valueOf(req.getParameter("startDate")))
        .setEndDate(Date.valueOf(req.getParameter("endDate")))
        .setTopicId(Integer.parseInt(req.getParameter("topicId")))
        .setTeacherId(Integer.parseInt(req.getParameter("teacherId")))
        .build();
  }

}