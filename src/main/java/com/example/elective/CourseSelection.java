package com.example.elective;

import java.util.Arrays;

public class CourseSelection {

  private int teacherId;
  private int topicId;
  private SortType sort = SortType.NONE;

  public CourseSelection(int teacherId, int topicId) {
    this.teacherId = teacherId;
    this.topicId = topicId;
  }

  public CourseSelection(int teacherId, int topicId, String sort) {
    this(teacherId, topicId);
    if (isSortValid(sort)) {
      this.sort = SortType.valueOf(sort.toUpperCase());
    }
  }

  private boolean isSortValid(String sort) {
    return Arrays
        .stream(SortType.values())
        .anyMatch(val -> val.toString().equals(sort.toUpperCase()));
  }

  public int getTeacherId() {
    return teacherId;
  }

  public int getTopicId() {
    return topicId;
  }

  public SortType getSort() {
    return sort;
  }

  public enum SortType {
    NAME("name"),
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
