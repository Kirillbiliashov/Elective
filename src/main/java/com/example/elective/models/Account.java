package com.example.elective.models;

/**
 * Model representation of account table
 * @author Kirill Biliashov
 */

public class Account extends Entity {

  private final Builder builder;
  private String username;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String role;

  public Account(Builder builder) {
    this.builder = builder;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Builder getBuilder() {
    return builder;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public String getRole() {
    return role;
  }

  public static class Builder {
    private final Account account = new Account(this);

    public Builder setId(int id) {
      if (account.id == 0) account.id = id;
      return this;
    }

    public Builder setUsername(String username) {
      account.username = username;
      return this;
    }

    public Builder setEmail(String email) {
      account.email = email;
      return this;
    }

    public Builder setPassword(String password) {
      account.password = password;
      return this;
    }

    public Builder setFirstName(String firstName) {
      account.firstName = firstName;
      return this;
    }

    public Builder setLastName(String lastName) {
      account.lastName = lastName;
      return this;
    }

    public Builder setRole(String role) {
      account.role = role;
      return this;
    }

    public Account build() {
      return account;
    }

  }

}
