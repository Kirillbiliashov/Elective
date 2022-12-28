package com.example.elective;

import java.util.Arrays;

public class CourseSelection {

  private String teacher = "Any";
  private String topic = "Any";
  private SortType sort = SortType.NONE;

  public CourseSelection(String teacher, String topic, String sort) {
    if (sort != null && isSortValid(sort)) {
      this.sort = SortType.valueOf(sort.toUpperCase());
    }
    if (teacher != null) {
      this.teacher = teacher;
    }
    if (topic != null) {
      this.topic = topic;
    }
  }

  private boolean isSortValid(String sort) {
    return Arrays
        .stream(SortType.values())
        .anyMatch(val -> val.toString().equals(sort.toUpperCase()));
  }

  public String getTeacher() {
    return teacher;
  }

  public String getTopic() {
    return topic;
  }

  public SortType getSort() {
    return sort;
  }

  public enum SortType {
    NAME("course.name"),
    NAME_DESC("name DESC"),
    DURATION("(end_date - start_date)"),
    DURATION_DESC("(end_date - start_date) DESC"),
    STUDENTS("students"),
    STUDENTS_DESC("students_desc"),
    NONE("none");

    private String orderByStr;

    public String getOrderBy() {
      return orderByStr;
    }

     SortType(String orderByStr) {
      this.orderByStr = orderByStr;
    }
  }

}
