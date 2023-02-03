package com.example.elective.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * Model representation of topic table
 * @author Kirill Biliashov
 */

@Entity
@Table(name = "topic")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  @OneToMany(mappedBy = "topic")
  private List<Course> courses;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

}
