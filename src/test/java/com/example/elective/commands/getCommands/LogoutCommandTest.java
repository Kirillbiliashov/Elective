package com.example.elective.commands.getCommands;

import com.example.elective.commands.getCommands.LogoutCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.*;
import static org.mockito.Mockito.*;

public class LogoutCommandTest {

  private static final LogoutCommand command = new LogoutCommand();

  @Mock
  private HttpSession session;

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testLogout() throws Exception {
    when(req.getSession()).thenReturn(session);
    command.init(context, req, resp);
    command.process();
    verify(session, times(1)).setAttribute(ACCOUNT_ATTR, null);
    verify(session, times(1)).setAttribute(HOME_URL_ATTR, LOGIN_URL);
    verify(resp, times(1)).sendRedirect(LOGIN_URL);
  }

}
