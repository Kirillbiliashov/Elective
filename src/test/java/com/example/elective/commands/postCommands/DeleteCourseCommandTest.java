package com.example.elective.commands.postCommands;

import com.example.elective.exceptions.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.ADMIN_URL;
import static org.mockito.Mockito.*;

public class DeleteCourseCommandTest extends CourseCommandTest {

  private final static String PATH_INFO = "/4";
  private final DeleteCourseCommand command = new DeleteCourseCommand();
  private final int ID = 4;

  @Override
  @BeforeEach
  void beforeEach() {
    super.beforeEach();
    command.init(context, req, resp);
    when(req.getPathInfo()).thenReturn(PATH_INFO);
  }

  @Override
  @Test
  void testPositiveScenario() throws Exception {
    command.process();
    verify(courseService, times(1)).delete(ID);
    verify(resp, times(1)).sendRedirect(ADMIN_URL);
  }

}