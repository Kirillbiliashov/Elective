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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DeleteCourseServletTest extends CourseServletTest {

  private DeleteCourseServlet servlet = new DeleteCourseServlet();

  @Override
  @Test
  void testPositiveScenario() throws Exception {
    servlet.init(config);
    when(req.getPathInfo()).thenReturn("/4");
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Override
  @Test
  void testNegativeScenario() throws Exception {
    servlet.init(config);
    when(req.getPathInfo()).thenReturn("/4");
    Mockito.doThrow(ServiceException.class).when(courseService).delete(anyInt());
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
