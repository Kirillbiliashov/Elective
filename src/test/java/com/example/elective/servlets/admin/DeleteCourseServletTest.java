package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ADMIN_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DeleteCourseServletTest extends CourseServletTest {

  private final static DeleteCourseServlet servlet = new DeleteCourseServlet();
  private final static String PATH_INFO = "/4";

  @Override
  @BeforeEach
  void beforeEach() {
    super.beforeEach();
    servlet.init(config);
    when(req.getPathInfo()).thenReturn(PATH_INFO);
  }

  @Override
  @Test
  void testPositiveScenario() throws Exception {
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(ADMIN_URL);
  }

  @Override
  @Test
  void testNegativeScenario() throws Exception {
    Mockito.doThrow(ServiceException.class).when(courseService).delete(anyInt());
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
