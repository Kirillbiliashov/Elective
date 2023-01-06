package com.example.elective.dto;

import java.sql.Date;

public class CourseDTO {

  protected int id;
  protected String name;
  protected String description;
  protected Date startDate;
  protected Date endDate;
  protected String topic;
  protected String teacher;
  protected int studentsCount;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }


  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getTopic() {
    return topic;
  }

  public String getTeacher() {
    return teacher;
  }

  public int getStudentsCount() {
    return studentsCount;
  }

  public int getDuration() {
    return this.endDate.compareTo(this.startDate);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {

    private final CourseDTO courseDTO = new CourseDTO();

    public Builder setId(int id) {
      courseDTO.id = id;
      return this;
    }

    public Builder setName(String name) {
      courseDTO.name = name;
      return this;
    }

    public Builder setDescription(String description) {
      courseDTO.description = description;
      return this;
    }

    public Builder setStartDate(Date startDate) {
      courseDTO.startDate = startDate;
      return this;
    }

    public Builder setEndDate(Date endDate) {
      courseDTO.endDate = endDate;
      return this;
    }

    public Builder setTopic(String topic) {
      courseDTO.topic = topic;
      return this;
    }

    public Builder setTeacher(String teacher) {
      courseDTO.teacher = teacher;
      return this;
    }

    public Builder setStudentsCount(int studentsCount) {
      courseDTO.studentsCount = studentsCount;
      return this;
    }

    public CourseDTO build() {
      return courseDTO;
    }

  }

}
