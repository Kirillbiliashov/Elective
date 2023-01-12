package com.example.elective.models;

/**
 * Model representation of topic table
 * @author Kirill Biliashov
 */

public class Topic extends Entity {

  private final String name;

  public Topic(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
