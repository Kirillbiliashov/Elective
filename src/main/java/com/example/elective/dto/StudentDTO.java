package com.example.elective.dto;

public class StudentDTO {

  private int id;
  private String fullName;
  private String username;
  private String email;
  private boolean isBlocked;

  public int getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }


  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public StudentDTO setId(int id) {
    this.id = id;
    return this;
  }

  public StudentDTO setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public StudentDTO setUsername(String username) {
    this.username = username;
    return this;
  }

  public StudentDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public StudentDTO setBlocked(boolean blocked) {
    isBlocked = blocked;
    return this;
  }

}
