package com.example.elective.dto;

import com.example.elective.models.Course;

import java.sql.Date;

public class RegisteredCourseDTO {

  private final CourseDTO courseDTO;
  private Date registrationDate;

  public RegisteredCourseDTO(CourseDTO courseDTO, Date registrationDate) {
    this.courseDTO = courseDTO;
    this.registrationDate = registrationDate;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public int getId() {
    return courseDTO.id;
  }

  public String getName() {
    return courseDTO.name;
  }

  public String getDescription() {
    return courseDTO.description;
  }

  public Date getStartDate() {
    return courseDTO.startDate;
  }

  public Date getEndDate() {
    return courseDTO.endDate;
  }

  public int getDuration() {
    return courseDTO.getDuration();
  }

  public String getTopic() {
    return courseDTO.topic;
  }

  public String getTeacher() {
    return courseDTO.teacher;
  }

  public int getStudentsCount() {
    return courseDTO.studentsCount;
  }

}
