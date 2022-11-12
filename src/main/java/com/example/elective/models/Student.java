package com.example.elective.models;

public class Student {

  private int id;
  private String firstName;
  private String lastName;
  private boolean isBlocked = false;
  private int accountId;

  public int getId() {
    return id;
  }

  public Student setId(int id) {
    this.id = id;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Student setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Student setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public Student setBlocked(boolean blocked) {
    isBlocked = blocked;
    return this;
  }

  public int getAccountId() {
    return accountId;
  }

  public Student setAccountId(int accountId) {
    this.accountId = accountId;
    return this;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", isBlocked=" + isBlocked +
        ", accountId=" + accountId +
        '}';
  }
}
