package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.CourseDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.models.Topic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.List;

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

  @Mock
  private Course course;
  @Mock
  private Topic topic;
  @Mock
  private Account teacher;
  @Mock
  private List<Journal> students;

  @BeforeEach
  public void before() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testMapping() {
    when(teacher.getFullName()).thenReturn(TEACHER);
    when(topic.getName()).thenReturn(TOPIC);
    when(course.getId()).thenReturn(ID);
    when(course.getName()).thenReturn(NAME);
    when(course.getDescription()).thenReturn(DESCRIPTION);
    when(course.getStartDate()).thenReturn(START_DATE);
    when(course.getEndDate()).thenReturn(END_DATE);
    when(course.getTopic()).thenReturn(topic);
    when(course.getTeacher()).thenReturn(teacher);
    when(course.getStudents()).thenReturn(students);
    when(students.size()).thenReturn(STUDENTS_COUNT);
    CourseDTOMapper mapper = new CourseDTOMapper();
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
