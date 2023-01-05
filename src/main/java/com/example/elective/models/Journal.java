package com.example.elective.models;

import java.sql.Date;

public class Journal extends Entity {

  private int grade;
  private Date enrollmentDate;
  private int courseId;
  private int studentId;
  private JournalBuilder builder;

  private Journal(JournalBuilder builder) {
    this.builder = builder;
  }

  public JournalBuilder getBuilder() {
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

  public static JournalBuilder newBuilder() {
    return new JournalBuilder();
  }


  public static class JournalBuilder {
    private final Journal journal = new Journal(this);

    public JournalBuilder setId(int id) {
      if (journal.id == 0) journal.id = id;
      return this;
    }

    public JournalBuilder setGrade(int grade) {
      journal.grade = grade;
      return this;
    }

    public JournalBuilder setEnrollmentDate(Date enrollmentDate) {
      journal.enrollmentDate = enrollmentDate;
      return this;
    }

    public JournalBuilder setCourseId(int courseId) {
      journal.courseId = courseId;
      return this;
    }

    public JournalBuilder setStudentId(int studentId) {
      journal.studentId = studentId;
      return this;
    }

    public Journal build() {
      return journal;
    }

  }

}
