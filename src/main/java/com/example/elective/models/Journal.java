package com.example.elective.models;

import java.sql.Date;

public class Journal extends Entity {

  private int grade;
  private Date enrollmentDate;
  private int courseId;
  private int studentId;

  @Override
  public Journal setId(int id) {
    super.setId(id);
    return this;
  }

  public int getGrade() {
    return grade;
  }

  public Journal setGrade(int grade) {
    this.grade = grade;
    return this;
  }

  public Date getEnrollmentDate() {
    return enrollmentDate;
  }

  public Journal setEnrollmentDate(Date enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
    return this;
  }

  public int getCourseId() {
    return courseId;
  }

  public Journal setCourseId(int courseId) {
    this.courseId = courseId;
    return this;
  }

  public int getStudentId() {
    return studentId;
  }

  public Journal setStudentId(int studentId) {
    this.studentId = studentId;
    return this;
  }
}
