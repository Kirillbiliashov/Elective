package com.example.elective.models;

public class Account extends Entity {

  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private boolean isBlocked;
  private int roleId;
  private AccountBuilder builder;

  public Account(AccountBuilder builder) {
    this.builder = builder;
  }

  public AccountBuilder getBuilder() {
    return builder;
  }

  public String getLogin() {
    return login;
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

  public boolean isBlocked() {
    return isBlocked;
  }

  public int getRoleId() {
    return roleId;
  }

  public static AccountBuilder newBuilder() {
    return new AccountBuilder();
  }

  public static class AccountBuilder {
    private final Account account = new Account(this);

    public AccountBuilder setId(int id) {
      if (account.id == 0) account.id = id;
      return this;
    }

    public AccountBuilder setLogin(String login) {
      account.login = login;
      return this;
    }

    public AccountBuilder setPassword(String password) {
      account.password = password;
      return this;
    }

    public AccountBuilder setFirstName(String firstName) {
      account.firstName = firstName;
      return this;
    }

    public AccountBuilder setLastName(String lastName) {
      account.lastName = lastName;
      return this;
    }

    public AccountBuilder setBlocked(boolean isBlocked) {
      account.isBlocked = isBlocked;
      return this;
    }

    public AccountBuilder setRoleId(int roleId) {
      account.roleId = roleId;
      return this;
    }

    public Account build() {
      return account;
    }

  }

}
