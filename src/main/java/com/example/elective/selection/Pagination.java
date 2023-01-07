package com.example.elective.selection;

public class Pagination {

  protected int page;
  protected int displayCount;
  protected int totalItems;

  public Pagination(int page, int displayCount, int totalItems) {
    this.page = page;
    this.displayCount = displayCount;
    this.totalItems = totalItems;
  }

  protected Pagination() {

  }

  public int getFrom() {
    return (page - 1) * displayCount;
  }

  public int getDisplayCount() {
    return displayCount;
  }

  public int getPagesCount() {
    return (int) Math.ceil(totalItems / (double) displayCount);
  }

}
