package com.example.elective.utils;

import java.util.Objects;

public record CourseSelection(String sort, String teacher, String topic) {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CourseSelection that = (CourseSelection) o;
    return sort.equals(that.sort) && teacher.equals(that.teacher) && topic.equals(that.topic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sort, teacher, topic);
  }
}
