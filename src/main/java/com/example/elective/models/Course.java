package com.example.elective.models;

import java.sql.Date;

public class Course extends Entity {

  private String name;
  private Date startDate;
  private Date endDate;
  private int topicId;
  private int teacherId;

  @Override
  public Course setId(int id) {
    super.setId(id);
    return this;
  }

  public String getName() {
    return name;
  }

  public Course setName(String name) {
    this.name = name;
    return this;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Course setEndDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Course setStartDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public int getTopicId() {
    return topicId;
  }

  public Course setTopicId(int topicId) {
    this.topicId = topicId;
    return this;
  }

  public int getTeacherId() {
    return teacherId;
  }

  public Course setTeacherId(int teacherId) {
    this.teacherId = teacherId;
    return this;
  }

}
