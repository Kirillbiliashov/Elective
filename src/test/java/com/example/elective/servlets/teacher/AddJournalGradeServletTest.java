package com.example.elective.servlets.teacher;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.JournalService;
import com.example.elective.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddJournalGradeServletTest {

  private final static String REDIRECT_URL = "/elective/teacher?page=1&display=1";
  private final static String PATH_INFO = "/1";
  private final AddJournalGradeServlet servlet = new AddJournalGradeServlet();

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

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(config.getServletContext()).thenReturn(context);
    when(context.getAttribute(JOURNAL_SERVICE)).thenReturn(journalService);
    when(req.getPathInfo()).thenReturn(PATH_INFO);
    when(req.getParameter("grade")).thenReturn("80");
    when(req.getParameter(PAGE_ATTR)).thenReturn("1");
    when(req.getParameter(DISPLAY_PARAM)).thenReturn("1");
    servlet.init(config);
  }

  @Test
  void testCourseEnroll() throws Exception {
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testCourseEnrollNegative() throws Exception {
    Mockito.doThrow(ServiceException.class).when(journalService)
        .updateGrade(anyInt(), anyInt());
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
