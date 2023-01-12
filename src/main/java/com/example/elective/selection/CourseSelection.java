package com.example.elective.selection;

import com.example.elective.dto.CourseDTO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that encapsulates data about course selection, such as courses' teacher, topic and sort type
 * @author Kirill Biliashov
 */

public class CourseSelection {

  private static final String ANY = "Any";
  private final String teacher;
  private final String topic;
  private SortType sortType = SortType.NONE;

  public CourseSelection(String teacher, String topic, String sort) {
    if (sort != null && isSortValid(sort)) {
      this.sortType = SortType.valueOf(sort.toUpperCase());
    }
    this.teacher = teacher != null ? teacher : ANY;
    this.topic = topic != null ? topic : ANY;
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
        .filter(c -> c.getTopic().equals(topic) || topic.equals(ANY))
        .filter(c -> c.getTeacher().equals(teacher) || teacher.equals(ANY))
        .collect(Collectors.toList());
  }

}
