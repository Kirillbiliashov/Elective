package com.example.elective.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Table(name = "blocklist")
public class Blocklist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  private Account student;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Account getStudent() {
    return student;
  }

  public void setStudent(Account student) {
    this.student = student;
  }

}
