package com.example.elective.dto;

import java.sql.Date;

public class CourseDTO {

  private int id;
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private String topic;
  private String teacher;
  private int studentsCount;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  public int getStudentsCount() {
    return studentsCount;
  }

  public void setStudentsCount(int studentsCount) {
    this.studentsCount = studentsCount;
  }

  public int getDuration() {
    return this.endDate.compareTo(this.startDate);
  }

}
