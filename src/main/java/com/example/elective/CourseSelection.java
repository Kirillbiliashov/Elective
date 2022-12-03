package com.example.elective;

public class CourseSelection {

  private int teacherId;
  private int topicId;
  private SortType sort;

  public CourseSelection(int teacherId, int topicId, String sort) {
    this.teacherId = teacherId;
    this.topicId = topicId;
    this.sort = SortType.valueOf(sort.toUpperCase());
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
