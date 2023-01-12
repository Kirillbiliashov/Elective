package com.example.elective.services;

import com.example.elective.dao.DAOFactory;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JournalServiceTest {

  private final static JournalService service = new JournalService();

  @Mock
  private TransactionManager tm;

  @Mock
  private JournalDAO dao;
  @Mock
  private DAOFactory factory;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    service.daoFactory = factory;
  }

  @Test
  void testGetStudentsCount() throws Exception {
    final int courseId = 5;
    final int studsCount = 10;
    Mockito.when(factory.getJournalDAO()).thenReturn(dao);
    Mockito.when(dao.getStudentsCount(anyInt())).thenReturn(studsCount);
    Assertions.assertEquals(studsCount, service.getStudentsCount(tm, courseId));
    verify(tm, times(1)).initTransaction(dao);
  }

  @Test
  void testFindByCourseAndStudent() throws Exception {
    final int courseId = 5;
    final int studentId = 10;
    Optional<Journal> optJournal = Optional.of(
        Journal
            .newBuilder()
            .setCourseId(courseId)
            .setStudentId(studentId)
            .build()
    );
    Mockito.when(factory.getJournalDAO()).thenReturn(dao);
    Mockito.when(dao.findByCourseAndStudent(anyInt(), anyInt()))
        .thenReturn(optJournal);
    Assertions.assertEquals(optJournal,
        service.findByCourseAndStudent(tm, courseId, studentId));
    verify(tm, times(1)).initTransaction(dao);
  }

}
