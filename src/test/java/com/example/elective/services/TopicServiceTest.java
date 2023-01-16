package com.example.elective.services;

import com.example.elective.dao.DAOFactory;
import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.models.Topic;
import com.example.elective.services.impl.TopicServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TopicServiceTest {

  private final static TopicServiceImpl service = new TopicServiceImpl();

  @Mock
  private TransactionManager tm;
  @Mock
  private TopicDAO dao;
  @Mock
  private DAOFactory factory;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    service.daoFactory = factory;
  }

  @Test
  void testFind() throws Exception {
    final int id = 5;
    final String topicName = "topic";
    Optional<Topic> optTopic = Optional.of(new Topic(id, topicName));
    Mockito.when(factory.getTopicDAO()).thenReturn(dao);
    Mockito.when(dao.find(Mockito.anyInt())).thenReturn(optTopic);
    Assertions.assertEquals(optTopic, service.find(tm, id));
    verify(tm, times(1)).initTransaction(dao);
  }

}
