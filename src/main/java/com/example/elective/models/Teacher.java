package com.example.elective.models;

public class Teacher {

  private int id;
  private String firstName;
  private String lastName;
  private int accountId;

  public int getId() {
    return id;
  }

  public Teacher setId(int id) {
    this.id = id;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Teacher setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Teacher setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public int getAccountId() {
    return accountId;
  }

  public Teacher setAccountId(int accountId) {
    this.accountId = accountId;
    return this;
  }

}
