package com.example.elective.selection;

public class Pagination {

  private int page;
  private int displayCount;

  public Pagination(int page, int displayCount) {
    this.page = page;
    this.displayCount = displayCount;
  }

  public int getFrom() {
    return (page - 1) * displayCount;
  }

  public int getDisplayCount() {
    return displayCount;
  }

}
