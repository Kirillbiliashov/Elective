package com.example.elective.selection;

/**
 * Class containing data about current page to display for the client as well as number of items to be displayed
 * @author Kirill Biliashov
 */

public class Pagination {

  protected final int page;
  protected final int displayCount;
  protected final long totalItems;

  public Pagination(int page, int displayCount, long totalItems) {
    this.displayCount = displayCount;
    this.totalItems = totalItems;
    this.page = Math.min(page, getPagesCount());
  }

  public int getPage() {
    return this.page;
  }

  public int getFrom() {
    return totalItems != 0 ? (page - 1) * displayCount : 0;
  }

  public int getDisplayCount() {
    return displayCount;
  }

  public int getPagesCount() {
    return (int) Math.ceil(totalItems / (double) displayCount);
  }

}
