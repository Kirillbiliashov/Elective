package com.example.elective.models;

import java.sql.Date;

public class Course {
  private int id;
  private String name;
  private int duration;
  private Date startDate;
  private int topicId;
  private int teacherId;

  public int getId() {
    return id;
  }

  public Course setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Course setName(String name) {
    this.name = name;
    return this;
  }

  public int getDuration() {
    return duration;
  }

  public Course setDuration(int duration) {
    this.duration = duration;
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
