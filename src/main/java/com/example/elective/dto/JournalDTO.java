package com.example.elective.dto;

/**
 * Data Transfer Object for journal entity; contains all necessary data about journal for client
 * @author Kirill Biliashov
 */

public class JournalDTO {

  private final int id;
  private final int grade;
  private final String student;

  public JournalDTO(int id, int grade, String student) {
    this.id = id;
    this.grade = grade;
    this.student = student;
  }

  public int getId() {
    return id;
  }

  public int getGrade() {
    return grade;
  }

  public String getStudent() {
    return student;
  }

}
