package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

public class CompletedCourseDTOMapper extends CourseMapper<CompletedCourseDTO> {

  private final CourseDTOMapper mapper;
  private final int grade;

  public CompletedCourseDTOMapper(String teacher, String topic,
                                  int grade, int studentsCount) {
    super(teacher, topic, studentsCount);
    this.mapper = new CourseDTOMapper(teacher, topic, studentsCount);
    this.grade = grade;
  }

  public CompletedCourseDTO map(Course course) throws MappingException {
    CourseDTO dto = mapper.map(course);
    return new CompletedCourseDTO(dto, grade);
  }

}
