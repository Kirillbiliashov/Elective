package com.example.elective.utils;

public class Pagination {

  private final Integer size;
  private final Integer page;

  public Pagination(Integer size, Integer page) {
    this.size = size;
    this.page = page;
  }

  public Integer getSize() {
    return size;
  }

  public Integer getPage() {
    return page;
  }

  public boolean isPaginated() {
    return page != null && size != null;
  }

}
