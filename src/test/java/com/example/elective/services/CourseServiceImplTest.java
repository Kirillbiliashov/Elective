package com.example.elective.services;

import com.example.elective.dao.DAOFactory;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.dto.CourseDTO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Topic;
import com.example.elective.selection.CourseSelection;
import com.example.elective.services.impl.AccountServiceImpl;
import com.example.elective.services.impl.CourseServiceImpl;
import com.example.elective.services.impl.JournalServiceImpl;
import com.example.elective.services.impl.TopicServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CourseServiceImplTest {

  private static final int RETURN_COURSES_COUNT = 5;

  private CourseServiceImpl service;

  @Mock
  private CourseDAO dao;
  @Mock
  private DAOFactory factory;
  @Mock
  private TransactionManager tm;
  @Mock
  private AccountServiceImpl accService;
  @Mock
  private TopicServiceImpl topicService;
  @Mock
  private JournalServiceImpl journalService;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    service = new CourseServiceImpl(topicService, accService, journalService);
    service.daoFactory = factory;
    when(factory.getCourseDAO()).thenReturn(dao);
  }

  @Test
  void testGetBySelection() throws Exception {
    final String topic = "topic2";
    final int expSize = 1;
    try (MockedStatic<TransactionManager> mockedStatic =
             Mockito.mockStatic(TransactionManager.class)) {
      mockedStatic.when(TransactionManager::getInstance).thenReturn(tm);
      returnMockedFromTopicService();
      returnMockedFromAccService();
      returnMockedFromJournalService();
      when(dao.getAll()).thenReturn(getCourseList());
      CourseSelection selection = new CourseSelection(null, topic, null);
      List<CourseDTO> dtoList = service.getBySelection(selection);
      Assertions.assertEquals(expSize, dtoList.size());
      CourseDTO dto = dtoList.get(0);
      Assertions.assertEquals(topic, dto.getTopic());
      verify(tm, times(1)).initTransaction(dao);
    }
  }

  private void returnMockedFromTopicService() throws Exception {
    int idx = 1;
    when(topicService.find(any(), anyInt())).thenReturn(
        Optional.of(new Topic(idx, "topic" + idx++)),
        Optional.of(new Topic(idx, "topic" + idx++)),
        Optional.of(new Topic(idx, "topic" + idx++)),
        Optional.of(new Topic(idx, "topic" + idx++)),
        Optional.of(new Topic(idx, "topic" + idx++))
        );
  }

  private void returnMockedFromAccService() throws Exception {
    int idx = 1;
    when(accService.find(any(), anyInt())).thenReturn(
        Optional.of(Account.newBuilder()
            .setFirstName("name" + idx)
            .setLastName("lastName" + idx++)
            .build()), Optional.of(Account.newBuilder()
            .setFirstName("name" + idx)
            .setLastName("lastName" + idx++)
            .build()), Optional.of(Account.newBuilder()
            .setFirstName("name" + idx)
            .setLastName("lastName" + idx++)
            .build()), Optional.of(Account.newBuilder()
            .setFirstName("name" + idx)
            .setLastName("lastName" + idx++)
            .build()), Optional.of(Account.newBuilder()
            .setFirstName("name" + idx)
            .setLastName("lastName" + idx)
            .build())
    );
  }

  private void returnMockedFromJournalService() throws Exception {
    when(journalService.getStudentsCount(any(), anyInt()))
        .thenReturn(getRandom(), getRandom(), getRandom(), getRandom(), getRandom());
  }

  private List<Course> getCourseList() {
    List<Course> courseList = new ArrayList<>();
    for (int i = 1; i <= RETURN_COURSES_COUNT; i++) {
      courseList.add(Course.newBuilder()
          .setId(i)
          .setName("name" + i)
          .setDescription("description" + i)
          .build());
    }
    return courseList;
  }

  private int getRandom() {
    final int maxNum = 10;
    return (int) (Math.random() * maxNum);
  }

}
