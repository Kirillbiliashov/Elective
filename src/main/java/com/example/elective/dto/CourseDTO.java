package com.example.elective.dto;

import java.sql.Date;

public class CourseDTO {

  private int id;
  private String name;
  private Date startDate;
  private Date endDate;
  private String topic;
  private String teacher;

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
}
