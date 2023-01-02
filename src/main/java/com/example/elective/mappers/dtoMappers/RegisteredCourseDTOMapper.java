package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

import java.sql.Date;

public class RegisteredCourseDTOMapper extends CourseMapper<RegisteredCourseDTO> {

  private Date registrationDate;

  public RegisteredCourseDTOMapper(Date registrationDate, String teacher, String topic) {
    super(teacher, topic);
    this.registrationDate = registrationDate;
  }

  public RegisteredCourseDTO map(Course course) throws MappingException {
    RegisteredCourseDTO dto = new RegisteredCourseDTO();
    mapCourseToDTO(course, dto);
    dto.setRegistrationDate(registrationDate);
    return dto;
  }

}