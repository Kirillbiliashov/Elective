package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

public class CompletedCourseDTOMapper extends CourseDTOMapper {

  private int grade;

  public CompletedCourseDTOMapper(String teacher, String topic,
                                  int grade, int studentsCount) {
    super(teacher, topic, studentsCount);
    this.grade = grade;
  }

  public CompletedCourseDTO map(Course course) throws MappingException {
    CompletedCourseDTO dto = (CompletedCourseDTO) super.map(course);
    dto.setGrade(grade);
    return dto;
  }

}
