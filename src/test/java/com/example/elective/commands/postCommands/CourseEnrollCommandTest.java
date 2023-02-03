package com.example.elective.commands.postCommands;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;
import com.example.elective.services.impl.JournalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;
import static com.example.elective.utils.Constants.JOURNAL_SERVICE;
import static org.mockito.Mockito.*;

public class CourseEnrollCommandTest {

  private final static String REDIRECT_URL = "/elective/student";
  private final static String PATH_INFO = "/1";
  private static final String COURSE_ID_PARAM = "1";
  private final CourseEnrollCommand command = new CourseEnrollCommand();
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;
  @Mock
  private JournalServiceImpl journalService;
  @Mock
  private HttpSession session;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(req.getSession()).thenReturn(session);
    when(context.getAttribute(JOURNAL_SERVICE)).thenReturn(journalService);
    command.init(context, req, resp);
    when(req.getPathInfo()).thenReturn(PATH_INFO);
    when(req.getParameter("courseId")).thenReturn(COURSE_ID_PARAM);
    when(session.getAttribute(ACCOUNT_ATTR))
        .thenReturn(new Account().setId(1));
  }

  @Test
  void testCourseEnroll() throws Exception {
    command.process();
    verify(journalService, times(1)).save(1, 1);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

}