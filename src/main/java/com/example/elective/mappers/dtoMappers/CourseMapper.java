package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

public abstract class CourseMapper<T> implements Mapper<Course, T> {

  protected String teacher;
  protected String topic;
  protected int studentsCount;

  public CourseMapper(String teacher, String topic, int studentsCount) {
    this.teacher = teacher;
    this.topic = topic;
    this.studentsCount = studentsCount;
  }

}
