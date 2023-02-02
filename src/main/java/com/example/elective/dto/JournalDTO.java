package com.example.elective.dto;

/**
 * Data Transfer Object for journal entity; contains all necessary data about journal for client
 * @author Kirill Biliashov
 */

public class JournalDTO {

  private int id;
  private int grade;
  private String student;

  public int getId() {
    return id;
  }

  public JournalDTO setId(int id) {
    this.id = id;
    return this;
  }

  public int getGrade() {
    return grade;
  }

  public JournalDTO setGrade(int grade) {
    this.grade = grade;
    return this;
  }

  public String getStudent() {
    return student;
  }

  public JournalDTO setStudent(String student) {
    this.student = student;
    return this;
  }
}
