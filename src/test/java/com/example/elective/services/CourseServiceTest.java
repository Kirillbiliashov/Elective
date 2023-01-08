package com.example.elective.services;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.DAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.dto.CourseDTO;
import com.example.elective.models.Course;
import com.example.elective.selection.CourseSelection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

  @Spy
  private CourseServiceTestClass service;

  @Mock
  private TransactionManager transactionManager;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    service.setTransactionManager(transactionManager);
  }

  @Test
  void testUpdate() throws Exception {
    Mockito.doNothing().when(service).performDaoWriteOperation(any());
    service.update(Mockito.mock(Course.class));
    verify(transactionManager, times(1)).initTransaction(any(DAO.class));
    verify(service, times(1)).performDaoWriteOperation(any());
  }

  @Test
  void getBySelection() throws Exception {
    CourseSelection selection = new CourseSelection("Teacher", "Topic", "name");
    List<CourseDTO> courses = createCourses(
        Arrays.asList("algebra", "geometry", "history", "mobile development", "physics"),
        Arrays.asList("Teacher", "Invalid teacher", "Invalid teacher", "Teacher", "Invalid teacher"),
        Arrays.asList("Topic", "Invalid topic", "Invalid topic", "Topic", "Invalid topic"));
    doReturn(courses).when(service).performDaoReadOperation(any());
    List<CourseDTO> expCourses = Arrays.asList(courses.get(0), courses.get(3));
    Assertions.assertEquals(expCourses, service.getBySelection(selection));
  }

  private List<CourseDTO> createCourses(List<String> names, List<String> teachers,
                                        List<String> topics) {
    List<CourseDTO> courses = new ArrayList<>();
    for (int i = 0; i < names.size(); i++) {
      courses.add(CourseDTO.newBuilder()
          .setName(names.get(i))
          .setTeacher(teachers.get(i))
          .setTopic(topics.get(i))
          .build());
    }
    return courses;
  }

  @Test
  void testSave() throws Exception {
    doNothing().when(service).performDaoWriteOperation(any(DAOWriteOperation.class));
    service.save(any(Course.class));
    verify(transactionManager, times(1)).initTransaction(any(CourseDAO.class));
    verify(service, times(1)).performDaoWriteOperation(any());
  }

  @Test
  void testDelete() throws Exception {
    doNothing().when(service).performDaoWriteOperation(any(DAOWriteOperation.class));
    service.delete(anyInt());
    verify(transactionManager, times(1)).initTransaction(any(CourseDAO.class));
    verify(service, times(1)).performDaoWriteOperation(any());
  }


  private static class CourseServiceTestClass extends CourseService {
    public void setTransactionManager(TransactionManager transactionManager) {
      this.transactionManager = transactionManager;
    }
  }

}
