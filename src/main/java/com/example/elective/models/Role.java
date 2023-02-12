package com.example.elective.models;

public enum Role {
  ROLE_ADMIN {
    @Override
    public String getHomeUrl() {
      return "/courses/all";
    }
  }, ROLE_TEACHER {
    @Override
    public String getHomeUrl() {
      return "/teachers/teacher";
    }
  }, ROLE_STUDENT {
    @Override
    public String getHomeUrl() {
      return "/courses/available";
    }
  };

  public abstract String getHomeUrl();
}
