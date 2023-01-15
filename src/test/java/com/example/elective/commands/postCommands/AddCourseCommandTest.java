package com.example.elective.commands.postCommands;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddCourseCommandTest extends CourseCommandTest {

  private final AddCoursePostCommand command = new AddCoursePostCommand();

  @Override
  @BeforeEach
  void beforeEach() {
    super.beforeEach();
    command.init(context, req, resp);
    mockRequestParams();
  }

  @Override
  @Test
  void testPositiveScenario() throws Exception {
    command.process();
    verify(courseService, times(1)).save(any(Course.class));
    verify(resp, times(1)).sendRedirect(Constants.ADMIN_URL);
  }

  @Override
  @Test
  void testNegativeScenario() throws Exception {
    Mockito.doThrow(ServiceException.class).when(courseService).save(any(Course.class));
    command.process();
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }
}
