package com.example.elective.models;

import java.sql.Date;

/**
 * Model representation of course table
 * @author Kirill Biliashov
 */

public class Course extends Entity {

  private final Builder builder;
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private int topicId;
  private int teacherId;

  public Course(Builder builder) {
    this.builder = builder;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Builder getBuilder() {
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

  public static class Builder {

    private final Course course = new Course(this);

    public Builder setId(int id) {
      if (course.id == 0) course.id = id;
      return this;
    }

    public Builder setName(String name) {
      course.name = name;
      return this;
    }

    public Builder setDescription(String description) {
      course.description = description;
      return this;
    }

    public Builder setStartDate(Date startDate) {
      course.startDate = startDate;
      return this;
    }

    public Builder setEndDate(Date endDate) {
      course.endDate = endDate;
      return this;
    }

    public Builder setTopicId(int topicId) {
      course.topicId = topicId;
      return this;
    }

    public Builder setTeacherId(int teacherId) {
      course.teacherId = teacherId;
      return this;
    }

    public Course build() {
      return course;
    }

  }

}
