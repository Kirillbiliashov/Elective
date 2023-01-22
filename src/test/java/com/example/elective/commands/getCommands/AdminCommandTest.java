package com.example.elective.commands.getCommands;

import com.example.elective.selection.CourseSelection;
import com.example.elective.services.impl.AccountServiceImpl;
import com.example.elective.services.impl.CourseServiceImpl;
import com.example.elective.services.impl.TopicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.*;
import static org.mockito.Mockito.*;

public class AdminCommandTest {

  private final AdminCommand command = new AdminCommand();
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;
  @Mock
  private TopicServiceImpl topicService;
  @Mock
  private CourseServiceImpl courseService;
  @Mock
  private AccountServiceImpl accService;
  @Mock
  private RequestDispatcher dispatcher;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    when(context.getAttribute(TOPIC_SERVICE)).thenReturn(topicService);
    when(context.getAttribute(COURSE_SERVICE)).thenReturn(courseService);
    when(context.getAttribute(ACCOUNT_SERVICE)).thenReturn(accService);
    command.init(context, req, resp);
  }

  @Test
  void testAdminCommand() throws Exception {
    command.process();
    verify(req, times(1)).setAttribute(TOPICS_ATTR, topicService.getAll());
    verify(req, times(1)).setAttribute(COURSES_ATTR,
        courseService.getBySelection(any(CourseSelection.class)));
    verify(req, times(1)).setAttribute(TEACHERS_ATTR,
        accService.getTeachers());
    verify(req, times(1)).setAttribute(SORT_TYPES_ATTR, SORT_TYPES);
    verify(dispatcher, times(1)).forward(req, resp);
  }


}
