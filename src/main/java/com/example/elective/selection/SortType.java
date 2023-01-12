package com.example.elective.selection;

import com.example.elective.dto.CourseDTO;

import java.util.Comparator;
import java.util.List;

/**
 * Enum describing different sort algorithms applicable to courses with method that performs actual sorting
 * @author Kirill Biliashov
 */

public enum SortType {
  NAME {
    @Override
    public void sort(List<CourseDTO> courses) {
      courses.sort(Comparator.comparing(CourseDTO::getName));
    }
  },
  NAME_DESC {
    @Override
    public void sort(List<CourseDTO> courses) {
      courses.sort(Comparator.comparing(CourseDTO::getName).reversed());
    }
  },
  DURATION {
    @Override
    public void sort(List<CourseDTO> courses) {
      courses.sort(Comparator.comparing(CourseDTO::getDuration));
    }
  },
  DURATION_DESC {
    @Override
    public void sort(List<CourseDTO> courses) {
      courses.sort(Comparator.comparing(CourseDTO::getDuration).reversed());
    }
  },
  STUDENTS {
    @Override
    public void sort(List<CourseDTO> courses) {
      courses.sort(Comparator.comparingInt(CourseDTO::getStudentsCount));
    }
  },
  STUDENTS_DESC {
    @Override
    public void sort(List<CourseDTO> courses) {
      courses.sort(Comparator.comparingInt(CourseDTO::getStudentsCount).reversed());
    }
  },
  NONE {
    @Override
    public void sort(List<CourseDTO> courses) {
    }
  };

  public abstract void sort(final List<CourseDTO> courses);

}