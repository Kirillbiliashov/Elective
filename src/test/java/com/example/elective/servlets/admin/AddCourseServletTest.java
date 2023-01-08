package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class AddCourseServletTest extends CourseServletTest {

  private final static AddCourseServlet servlet = new AddCourseServlet();

  @Override
  @BeforeEach
  void beforeEach() {
    super.beforeEach();
    servlet.init(config);
    mockRequestParams();
  }

  @Override
  @Test
  void testPositiveScenario() throws Exception {
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(Constants.ADMIN_URL);
  }

  @Override
  @Test
  void testNegativeScenario() throws Exception {
    Mockito.doThrow(ServiceException.class).when(courseService).save(any(Course.class));
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
