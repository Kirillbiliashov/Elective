package com.example.elective.dto;

import java.sql.Date;

public class RegisteredCourseDTO extends CourseDTO {

  private Date registrationDate;

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }


}
