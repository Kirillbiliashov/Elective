package com.example.elective.dto;

import java.sql.Date;

/**
 * Data Transfer Object for course entity; contains all necessary data for client about course
 * @author Kirill Biliashov
 */

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

  public long getDuration() {
    return endDate.getTime() - startDate.getTime();
  }

  public CourseDTO setId(int id) {
    this.id = id;
    return this;
  }

  public CourseDTO setName(String name) {
    this.name = name;
    return this;
  }

  public CourseDTO setDescription(String description) {
    this.description = description;
    return this;
  }

  public CourseDTO setStartDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public CourseDTO setEndDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  public CourseDTO setTopic(String topic) {
    this.topic = topic;
    return this;
  }

  public CourseDTO setTeacher(String teacher) {
    this.teacher = teacher;
    return this;
  }

  public CourseDTO setStudentsCount(int studentsCount) {
    this.studentsCount = studentsCount;
    return this;
  }
}
