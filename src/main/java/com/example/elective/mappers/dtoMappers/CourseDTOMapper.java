package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;

public class CourseDTOMapper extends CourseMapper<CourseDTO> {

  public CourseDTOMapper(String teacher, String topic, int studentsCount) {
    super(teacher, topic, studentsCount);
  }

  @Override
  public CourseDTO map(Course course) throws MappingException {
    return CourseDTO.newBuilder()
        .setId(course.getId())
        .setName(course.getName())
        .setDescription(course.getDescription())
        .setStartDate(course.getStartDate())
        .setEndDate(course.getEndDate())
        .setTopic(topic)
        .setTeacher(teacher)
        .setStudentsCount(studentsCount)
        .build();
  }

}
