package com.example.elective.models;

/**
 * Abstract base class for all model representations containing id field and its getter
 * @author Kirill Biliashov
 */

public abstract class Entity {

  protected int id;

  public int getId() {
    return id;
  }

}
