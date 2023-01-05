package com.example.elective.selection;

import com.example.elective.dto.CourseDTO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CourseSelection {

  private String teacher = "Any";
  private String topic = "Any";
  private SortType sortType = SortType.NONE;

  public CourseSelection(String teacher, String topic, String sort) {
    if (sort != null && isSortValid(sort)) {
      this.sortType = SortType.valueOf(sort.toUpperCase());
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

  public List<CourseDTO> getSelected(List<CourseDTO> courses) {
    sortType.sort(courses);
    return courses
        .stream()
        .filter(c -> c.getTopic().equals(topic) || topic.equals("Any"))
        .filter(c -> c.getTeacher().equals(teacher) || teacher.equals("Any"))
        .collect(Collectors.toList());
  }

}
