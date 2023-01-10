package com.example.elective.selection;

/**
 * Class containing data about current page to display for the client as well as number of items to be displayed
 * @author Kirill Biliashov
 */

public class Pagination {

  protected int page;
  protected int displayCount;
  protected int totalItems;

  public Pagination(int page, int displayCount, int totalItems) {
    this.displayCount = displayCount;
    this.totalItems = totalItems;
    this.page = Math.min(page, getPagesCount());
  }

  public int getPage() {
    return this.page;
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
