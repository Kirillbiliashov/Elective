package com.example.elective.models;

public class Account {
  private int id;
  private String login;
  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Override
  public String toString() {
    return "Account{" +
        "id=" + id +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

}
