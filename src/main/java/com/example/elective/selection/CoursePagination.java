package com.example.elective.selection;

/**
 * Class that extends Pagination having displyCount always set to 1
 * @author Kirill Biliashov
 */

public class CoursePagination extends Pagination {

  public CoursePagination(int page, long totalItems) {
    super(page, 1, totalItems);
  }

}
