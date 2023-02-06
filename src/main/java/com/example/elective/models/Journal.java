package com.example.elective.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Model representation of journal table
 * @author Kirill Biliashov
 */

@Entity
@Table(name = "journal")
public class Journal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;

  private int grade;

  @Column(name = "enrollment_date")
  private Date enrollmentDate;

  @ManyToOne
  private Course course;

  @ManyToOne
  private Account student;

  public int getId() {
    return id;
  }

  public Journal setId(int id) {
    this.id = id;
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

  public Course getCourse() {
    return course;
  }

  public Journal setCourse(Course course) {
    this.course = course;
    return this;
  }

  public Account getStudent() {
    return student;
  }

  public Journal setStudent(Account student) {
    this.student = student;
    return this;
  }

}
