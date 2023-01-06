package com.example.elective.dto;

import com.example.elective.models.Course;
import com.example.elective.models.Journal;

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
  private CourseDTOBuilder builder;

  private CourseDTO(CourseDTOBuilder builder) {
    this.builder = builder;
  }

  protected CourseDTO() {}


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

  public static CourseDTOBuilder newBuilder() {
    return new CourseDTOBuilder();
  }

  public static class CourseDTOBuilder {

    protected CourseDTO courseDTO = new CourseDTO(this);

    public CourseDTOBuilder setId(int id) {
      courseDTO.id = id;
      return this;
    }

    public CourseDTOBuilder setName(String name) {
      courseDTO.name = name;
      return this;
    }

    public CourseDTOBuilder setDescription(String description) {
      courseDTO.description = description;
      return this;
    }

    public CourseDTOBuilder setStartDate(Date startDate) {
      courseDTO.startDate = startDate;
      return this;
    }

    public CourseDTOBuilder setEndDate(Date endDate) {
      courseDTO.endDate = endDate;
      return this;
    }

    public CourseDTOBuilder setTopic(String topic) {
      courseDTO.topic = topic;
      return this;
    }

    public CourseDTOBuilder setTeacher(String teacher) {
      courseDTO.teacher = teacher;
      return this;
    }

    public CourseDTOBuilder setStudentsCount(int studentsCount) {
      courseDTO.studentsCount = studentsCount;
      return this;
    }

    public CourseDTO build() {
      return courseDTO;
    }

  }

}
