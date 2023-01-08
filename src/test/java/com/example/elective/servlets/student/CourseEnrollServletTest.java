package com.example.elective.servlets.student;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.services.CourseService;
import com.example.elective.services.JournalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;
import static com.example.elective.utils.Constants.JOURNAL_SERVICE;
import static org.mockito.Mockito.*;

public class CourseEnrollServletTest {

  private final CourseEnrollServlet servlet = new CourseEnrollServlet();

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse resp;

  @Mock
  private ServletConfig config;

  @Mock
  private ServletContext context;

  @Mock
  private JournalService journalService;

  @Mock
  private HttpSession session;

  private final static String REDIRECT_URL = "/elective/student";
  private final static String PATH_INFO = "/1";

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(config.getServletContext()).thenReturn(context);
    when(req.getSession()).thenReturn(session);
    when(context.getAttribute(JOURNAL_SERVICE)).thenReturn(journalService);
    servlet.init(config);
    when(req.getPathInfo()).thenReturn(PATH_INFO);
    when(session.getAttribute(ACCOUNT_ATTR))
        .thenReturn(Account.newBuilder().setId(1).build());
  }

  @Test
  void testCourseEnroll() throws IOException {
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testCourseEnrollNegative() throws Exception {
    Mockito.doThrow(ServiceException.class).when(journalService)
        .save(any(Journal.class));
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
