package com.example.elective.commands.getCommands;

import com.example.elective.models.Course;
import com.example.elective.selection.CourseSelection;
import com.example.elective.services.AccountService;
import com.example.elective.services.CourseService;
import com.example.elective.services.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.example.elective.utils.Constants.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class EditCoursesCommandTest {

  private static final String PATH_INFO = "/10";
  private final EditCourseGetCommand command = new EditCourseGetCommand();
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;
  @Mock
  private TopicService topicService;
  @Mock
  private CourseService courseService;
  @Mock
  private AccountService accService;
  @Mock
  private RequestDispatcher dispatcher;
  @Mock
  private Course course;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    when(req.getPathInfo()).thenReturn(PATH_INFO);
    when(context.getAttribute(TOPIC_SERVICE)).thenReturn(topicService);
    when(context.getAttribute(COURSE_SERVICE)).thenReturn(courseService);
    when(context.getAttribute(ACCOUNT_SERVICE)).thenReturn(accService);
    command.init(context, req, resp);
  }

  @Test
  void testAdminCommand() throws Exception {
    when(courseService.findById(anyInt())).thenReturn(Optional.of(course));
    command.process();
    verify(req, times(1)).setAttribute(COURSE_ATTR, course);
    verify(req, times(1)).setAttribute(TOPICS_ATTR, topicService.getAll());
    verify(req, times(1)).setAttribute(TEACHERS_ATTR,
        accService.getByRole(TEACHER_ROLE));
    verify(req, times(1)).getPathInfo();
    verify(dispatcher, times(1)).forward(req, resp);
  }

}
