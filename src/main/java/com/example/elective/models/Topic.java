package com.example.elective.models;

public class Topic extends Entity {

  private String name;

  public String getName() {
    return name;
  }

  @Override
  public Topic setId(int id) {
    super.setId(id);
    return this;
  }

  public Topic setName(String name) {
    this.name = name;
    return this;
  }

}
