package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

/**
 * Class that map Course to CourseDTO
 * @author Kirill Biliashov
 */

public class CourseDTOMapper implements Mapper<Course, CourseDTO> {

  @Override
  public CourseDTO map(Course course) {
    return CourseDTO.newBuilder()
        .setId(course.getId())
        .setName(course.getName())
        .setDescription(course.getDescription())
        .setStartDate(course.getStartDate())
        .setEndDate(course.getEndDate())
        .setTopic(course.getTopic().getName())
        .setTeacher(course.getTeacher().getFullName())
        .setStudentsCount(course.getStudents().size())
        .build();
  }

}
