package com.example.elective.mappers;

import com.example.elective.models.Course;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class CourseRequestMapper implements RequestMapper<Course>{
  @Override
  public Course map(HttpServletRequest req) {
    return new Course().setName(req.getParameter("name"))
        .setStartDate(Date.valueOf(req.getParameter("startDate")))
        .setEndDate(Date.valueOf(req.getParameter("endDate")))
        .setTopicId(Integer.parseInt(req.getParameter("topicId")))
        .setTeacherId(Integer.parseInt(req.getParameter("teacherId")));
  }
}
