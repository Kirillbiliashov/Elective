package com.example.elective.commands.postCommands;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.ADMIN_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EditCourseCommandTest extends CourseCommandTest {

  private final EditCoursePostCommand command = new EditCoursePostCommand();

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
    verify(courseService, times(1)).update(any(Course.class));
    verify(resp, times(1)).sendRedirect(ADMIN_URL);
  }

}