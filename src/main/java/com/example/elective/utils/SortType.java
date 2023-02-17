package com.example.elective.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

public enum SortType {
  NONE {
    @Override
    public Sort getSort() {
      return Sort.unsorted();
    }
  },
  NAME {
    @Override
    public Sort getSort() {
      return Sort.by("name");
    }
  }, NAME_DESC {
    @Override
    public Sort getSort() {
      return Sort.by(Sort.Direction.DESC, "name");
    }
  },
  DURATION {
    @Override
    public Sort getSort() {
      return JpaSort.unsafe("(endDate - startDate)");
    }
  },
  DURATION_DESC {
    @Override
    public Sort getSort() {
      return JpaSort.unsafe(Sort.Direction.DESC, "(endDate - startDate)");
    }
  },
  STUDENTS_COUNT {
    @Override
    public Sort getSort() {
      return JpaSort.unsafe("SIZE(c.students)");
    }
  },
  STUDENTS_COUNT_DESC {
    @Override
    public Sort getSort() {
      return  JpaSort.unsafe(Sort.Direction.DESC, "SIZE(c.students)");
    }
  };

  public abstract Sort getSort();
}
