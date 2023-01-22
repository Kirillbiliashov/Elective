package com.example.elective.models;

public class Blocklist extends Entity {

  private int studentId;

  public Blocklist(int id, int studentId) {
    super.id = id;
    this.studentId = studentId;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

}
