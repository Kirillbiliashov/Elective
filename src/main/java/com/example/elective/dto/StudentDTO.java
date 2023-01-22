package com.example.elective.dto;

public class StudentDTO {

  private int id;
  private String fullName;
  private String username;
  private String email;
  private boolean isBlocked;

  public static Builder newBuilder() {
    return new Builder();
  }

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

  public static class Builder {
    private final StudentDTO dto = new StudentDTO();

    public Builder setId(int id) {
      dto.id = id;
      return this;
    }

    public Builder setFullName(String fullName) {
      dto.fullName = fullName;
      return this;
    }

    public Builder setUsername(String username) {
      dto.username = username;
      return this;
    }

    public Builder setEmail(String email) {
      dto.email = email;
      return this;
    }

    public Builder setBlocked(boolean isBlocked) {
      dto.isBlocked = isBlocked;
      return this;
    }

    public StudentDTO build() {
      return dto;
    }

  }
}
