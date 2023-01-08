package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;

import static org.mockito.Mockito.*;

public class CourseDTOMapperTest {

  final int ID = 1;
  final String NAME = "OOP";
  final String DESCRIPTION = "OOP description";
  final Date START_DATE = Date.valueOf("2023-01-01");
  final Date END_DATE = Date.valueOf("2023-02-01");
  final String TEACHER = "Teacher Teacher";
  final String TOPIC = "Topic";
  final int STUDENTS_COUNT = 75;

  @Test
  void testMapping() throws MappingException {
    Course course = Mockito.mock(Course.class);
    when(course.getId()).thenReturn(ID);
    when(course.getName()).thenReturn(NAME);
    when(course.getDescription()).thenReturn(DESCRIPTION);
    when(course.getStartDate()).thenReturn(START_DATE);
    when(course.getEndDate()).thenReturn(END_DATE);
    CourseDTOMapper mapper = new CourseDTOMapper(TEACHER, TOPIC, STUDENTS_COUNT);
    CourseDTO dto = mapper.map(course);
    Assertions.assertEquals(dto.getId(), ID);
    Assertions.assertEquals(dto.getName(), NAME);
    Assertions.assertEquals(dto.getDescription(), DESCRIPTION);
    Assertions.assertEquals(dto.getStartDate(), START_DATE);
    Assertions.assertEquals(dto.getEndDate(), END_DATE);
    Assertions.assertEquals(dto.getTopic(), TOPIC);
    Assertions.assertEquals(dto.getTeacher(), TEACHER);
    Assertions.assertEquals(dto.getStudentsCount(), STUDENTS_COUNT);
  }

}
