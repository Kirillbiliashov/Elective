package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.services.CourseService;
import com.example.elective.services.TeacherService;
import com.example.elective.servlets.admin.AddCourseServlet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class AddCourseServletTest extends CourseServletTest {

  private AddCourseServlet servlet = new AddCourseServlet();

  @Override
  @Test
  void testPositiveScenario() throws Exception {
    servlet.init(config);
    mockRequestParams();
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Override
  @Test
  void testNegativeScenario() throws Exception {
    servlet.init(config);
    Mockito.doThrow(ServiceException.class).when(courseService).save(any(Course.class));
    mockRequestParams();
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
