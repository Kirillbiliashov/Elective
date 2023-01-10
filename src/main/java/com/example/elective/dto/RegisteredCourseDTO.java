package com.example.elective.dto;

import java.sql.Date;

/**
 * Data Transfer Object for course entity; contains all necessary data for client about registered course
 * @author Kirill Biliashov
 */

public class RegisteredCourseDTO {

  private final CourseDTO courseDTO;
  private final Date registrationDate;

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

  public long getDuration() {
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
