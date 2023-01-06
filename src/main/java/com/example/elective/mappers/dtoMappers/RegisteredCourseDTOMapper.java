package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

import java.sql.Date;

public class RegisteredCourseDTOMapper extends CourseMapper<RegisteredCourseDTO> {

  private final Date registrationDate;
  private final CourseDTOMapper mapper;

  public RegisteredCourseDTOMapper(Date registrationDate, String teacher,
                                   String topic, int studentsCount) {
    super(teacher, topic, studentsCount);
    this.mapper = new CourseDTOMapper(teacher, topic, studentsCount);
    this.registrationDate = registrationDate;
  }

  @Override
  public RegisteredCourseDTO map(Course course) throws MappingException {
    CourseDTO courseDto = mapper.map(course);
    return new RegisteredCourseDTO(courseDto, registrationDate);
  }

}
