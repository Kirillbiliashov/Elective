package com.example.elective.utils;

public class CourseSelection {

  private final String sort;
  private final String teacher;
  private final String topic;

  public CourseSelection(String sort, String teacher, String topic) {
    this.sort = sort;
    this.teacher = teacher;
    this.topic = topic;
  }

  public String getSort() {
    return sort;
  }

  public String getTeacher() {
    return teacher;
  }

  public String getTopic() {
    return topic;
  }
}
