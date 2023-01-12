package com.example.elective.models;

import java.sql.Date;

/**
 * Model representation of journal table
 * @author Kirill Biliashov
 */

public class Journal extends Entity {

  private int grade;
  private Date enrollmentDate;
  private int courseId;
  private int studentId;
  private final Builder builder;

  private Journal(Builder builder) {
    this.builder = builder;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Builder getBuilder() {
    return builder;
  }

  public int getGrade() {
    return grade;
  }

  public Date getEnrollmentDate() {
    return enrollmentDate;
  }

  public int getCourseId() {
    return courseId;
  }

  public int getStudentId() {
    return studentId;
  }

  public static class Builder {
    private final Journal journal = new Journal(this);

    public Builder setId(int id) {
      if (journal.id == 0) journal.id = id;
      return this;
    }

    public Builder setGrade(int grade) {
      journal.grade = grade;
      return this;
    }

    public Builder setEnrollmentDate(Date enrollmentDate) {
      journal.enrollmentDate = enrollmentDate;
      return this;
    }

    public Builder setCourseId(int courseId) {
      journal.courseId = courseId;
      return this;
    }

    public Builder setStudentId(int studentId) {
      journal.studentId = studentId;
      return this;
    }

    public Journal build() {
      return journal;
    }

  }

}
