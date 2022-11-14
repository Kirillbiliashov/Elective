package com.example.elective.models;

public class Topic {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public Topic setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Topic setName(String name) {
    this.name = name;
    return this;
  }
}
