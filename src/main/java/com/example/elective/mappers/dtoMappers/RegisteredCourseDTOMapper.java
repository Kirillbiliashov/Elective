package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

import java.sql.Date;

public class RegisteredCourseDTOMapper extends CourseDTOMapper {

  private Date registrationDate;

  public RegisteredCourseDTOMapper(Date registrationDate, String teacher,
                                   String topic, int studentsCount) {
    super(teacher, topic, studentsCount);
    this.registrationDate = registrationDate;
  }

  @Override
  public RegisteredCourseDTO map(Course course) throws MappingException {
    RegisteredCourseDTO dto = (RegisteredCourseDTO) super.map(course);
    dto.setRegistrationDate(registrationDate);
    return dto;
  }

}
