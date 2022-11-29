package com.example.elective.models;

public class Role extends Entity {

  private String name;

  @Override
  public Role setId(int id) {
    super.setId(id);
    return this;
  }


  public String getName() {
    return name;
  }

  public Role setName(String name) {
    this.name = name;
    return this;
  }

}
