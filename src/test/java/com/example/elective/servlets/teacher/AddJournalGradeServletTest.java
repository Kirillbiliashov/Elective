package com.example.elective.servlets.teacher;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;
import com.example.elective.services.JournalService;
import com.example.elective.servlets.student.CourseEnrollServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddJournalGradeServletTest {

  private AddJournalGradeServlet servlet = new AddJournalGradeServlet();

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

  private static String REDIRECT_URL = "/elective/teacher?page=1";

  @BeforeEach()
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(config.getServletContext()).thenReturn(context);
    when(context.getAttribute("journalService")).thenReturn(journalService);
    when(req.getPathInfo()).thenReturn("/1");
    when(req.getParameter("grade")).thenReturn("80");
    servlet.init(config);
  }

  @Test
  void testCourseEnroll() throws IOException {
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testCourseEnrollNegative() throws ServiceException, IOException {
    Mockito.doThrow(ServiceException.class).when(journalService).updateGradeById(anyInt(), anyInt());
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
