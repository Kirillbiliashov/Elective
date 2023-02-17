package com.example.elective.dto;

import java.util.Date;
import java.util.List;

public class TeacherCourseDTO {

  private String name;
  private Date startDate;
  private Date endDate;
  private int teacherId;
  private List<JournalDTO> journalList;

  public String getName() {
    return name;
  }

  public TeacherCourseDTO setName(String name) {
    this.name = name;
    return this;
  }

  public Date getStartDate() {
    return startDate;
  }

  public TeacherCourseDTO setStartDate(Date startDate) {
    this.startDate = startDate;
    return this;
  }

  public Date getEndDate() {
    return endDate;
  }

  public TeacherCourseDTO setEndDate(Date endDate) {
    this.endDate = endDate;
    return this;
  }

  public List<JournalDTO> getJournalList() {
    return journalList;
  }

  public TeacherCourseDTO setJournalList(List<JournalDTO> journalList) {
    this.journalList = journalList;
    return this;
  }

  public int getTeacherId() {
    return teacherId;
  }

  public TeacherCourseDTO setTeacherId(int teacherId) {
    this.teacherId = teacherId;
    return this;
  }
}
