package com.example.elective.commands.postCommands;

import com.example.elective.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.COURSE_SERVICE;
import static org.mockito.Mockito.when;

public abstract class CourseCommandTest {

  @Mock
  protected HttpServletRequest req;

  @Mock
  protected HttpServletResponse resp;

  @Mock
  protected ServletContext context;

  @Mock
  protected CourseService courseService;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(context.getAttribute(COURSE_SERVICE)).thenReturn(courseService);
  }

  @Test
  abstract void testPositiveScenario() throws Exception;

  @Test
  abstract void testNegativeScenario() throws Exception;

  protected void mockRequestParams() {
    when(req.getParameter("startDate")).thenReturn("2023-11-11");
    when(req.getParameter("endDate")).thenReturn("2023-12-12");
    when(req.getParameter("topicId")).thenReturn("1");
    when(req.getParameter("teacherId")).thenReturn("1");
  }

}
