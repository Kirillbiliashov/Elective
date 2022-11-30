package com.example.elective.models;

public class Topic extends Entity {

  private String name;

  public Topic(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Topic setName(String name) {
    this.name = name;
    return this;
  }

}
