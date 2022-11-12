package com.example.elective.models;

public class Account {

  private int id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private boolean isBlocked;
  private int roleId;

  public int getId() {
    return id;
  }

  public Account setId(int id) {
    this.id = id;
    return this;
  }

  public String getLogin() {
    return login;
  }

  public Account setLogin(String login) {
    this.login = login;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Account setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Account setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Account setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public Account setBlocked(boolean blocked) {
    isBlocked = blocked;
    return this;
  }

  public int getRoleId() {
    return roleId;
  }

  public Account setRoleId(int roleId) {
    this.roleId = roleId;
    return this;
  }
}
