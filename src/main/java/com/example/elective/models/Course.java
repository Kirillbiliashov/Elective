package com.example.elective.models;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

/**
 * Model representation of course table
 * @author Kirill Biliashov
 */

@Entity
@Table(name = "course")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 2, max = 50, message = "Name should be 2 to 50 characters long")
  private String name;
  @Size(min = 10, max = 1024, message = "description should be 10 to 1024 characters long")
  private String description;

  @FutureOrPresent(message = "start date can't be in the past")
  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topic_id", referencedColumnName = "id")
  private Topic topic;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", referencedColumnName = "id")
  private Account teacher;

  @OneToMany(mappedBy = "course")
  private List<Journal> students;

  public Integer getId() {
    return id;
  }

  public Course setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Course setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Course setDescription(String description) {
    this.description = description;
    return this;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Course setStartDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Course setEndDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  public Topic getTopic() {
    return topic;
  }

  public Course setTopic(Topic topic) {
    this.topic = topic;
    return this;
  }

  public Account getTeacher() {
    return teacher;
  }

  public Course setTeacher(Account teacher) {
    this.teacher = teacher;
    return this;
  }

  public List<Journal> getStudents() {
    return students;
  }

  public Course setStudents(List<Journal> students) {
    this.students = students;
    return this;
  }

}
