package com.example.elective.commands.postCommands;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.impl.JournalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.*;
import static org.mockito.Mockito.*;

public class AddGradeCommandTest {
  private final static String REDIRECT_URL = "/elective/teacher?page=1&display=1";
  private final static String PATH_INFO = "/1";
  private final AddGradeCommand command = new AddGradeCommand();

  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;
  @Mock
  private JournalServiceImpl journalService;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(context.getAttribute(JOURNAL_SERVICE)).thenReturn(journalService);
    when(req.getPathInfo()).thenReturn(PATH_INFO);
    when(req.getParameter("grade")).thenReturn("80");
    when(req.getParameter(PAGE_ATTR)).thenReturn("1");
    when(req.getParameter(DISPLAY_PARAM)).thenReturn("1");
    command.init(context, req, resp);
  }

  @Test
  void testCourseEnroll() throws Exception {
    command.process();
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

}
