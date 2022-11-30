package com.example.elective.models;

public class Role extends Entity {

  private String name;

  public Role(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Role setName(String name) {
    this.name = name;
    return this;
  }

}
