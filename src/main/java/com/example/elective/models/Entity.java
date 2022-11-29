package com.example.elective.models;

public abstract class Entity {

  private int id;

  public int getId() {
    return id;
  }

  protected Entity setId(int id) {
    if (this.id == 0) this.id = id;
    return this;
  }

}
