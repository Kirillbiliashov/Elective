package com.example.elective.models;

import java.sql.Date;

public class Course extends Entity {

  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private int topicId;
  private int teacherId;
  private CourseBuilder builder;

  public Course(CourseBuilder builder) {
    this.builder = builder;
  }

  public CourseBuilder getBuilder() {
    return builder;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public int getTopicId() {
    return topicId;
  }

  public int getTeacherId() {
    return teacherId;
  }

  public static CourseBuilder newBuilder() {
    return new CourseBuilder();
  }


  public static class CourseBuilder {

    private final Course course = new Course(this);

    public CourseBuilder setId(int id) {
      if (course.id == 0) course.id = id;
      return this;
    }

    public CourseBuilder setName(String name) {
      course.name = name;
      return this;
    }

    public CourseBuilder setDescription(String description) {
      course.description = description;
      return this;
    }

    public CourseBuilder setStartDate(Date startDate) {
      course.startDate = startDate;
      return this;
    }

    public  CourseBuilder setEndDate(Date endDate) {
      course.endDate = endDate;
      return this;
    }

    public CourseBuilder setTopicId(int topicId) {
      course.topicId = topicId;
      return this;
    }

    public CourseBuilder setTeacherId(int teacherId) {
      course.teacherId = teacherId;
      return this;
    }

    public Course build() {
      return course;
    }

  }

}
