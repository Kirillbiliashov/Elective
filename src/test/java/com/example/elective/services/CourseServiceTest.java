package com.example.elective.services;

import com.example.elective.CourseSelection;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.DAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.models.Course;
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
  CourseServiceTestClass service;

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
    CourseSelection selection = new CourseSelection(1, 1, "name");
    List<Course> courses = createCourses(
        Arrays.asList("algebra", "geometry", "history", "mobile development", "physics"),
        Arrays.asList(1, 1, 2, 3, 1),
        Arrays.asList(2, 1, 3, 3, 1));
    System.out.println(courses);
    doReturn(courses).when(service).performDaoReadOperation(any());
    List<Course> expCourses = Arrays.asList(courses.get(1), courses.get(4));
    System.out.println(expCourses);
    Assertions.assertEquals(expCourses, service.getBySelection(selection));
  }

  private List<Course> createCourses(List<String> names, List<Integer> teacherIds,
                                     List<Integer> topicIds) {
    List<Course> courses = new ArrayList<>();
    for (int i = 0; i < names.size(); i++) {
      courses.add(Course
          .newBuilder()
          .setName(names.get(i))
          .setTeacherId(teacherIds.get(i))
          .setTopicId(topicIds.get(i)).
          build());
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
