package com.example.elective.commands.getCommands;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;
import static com.example.elective.utils.Constants.LOGINS_ATTR;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TeacherRegistrationCommandTest {

  private final TeacherRegistrationGetCommand command =
      new TeacherRegistrationGetCommand();
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;
  @Mock
  private AccountServiceImpl accService;
  @Mock
  private RequestDispatcher dispatcher;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(context.getAttribute(ACCOUNT_SERVICE)).thenReturn(accService);
    command.init(context, req, resp);
  }

  @Test
  void testTeacherRegistrationForm() throws Exception {
    when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    command.process();
    verify(accService, times(1)).getLogins();
    verify(req, times(1)).setAttribute(LOGINS_ATTR, accService.getLogins());
    verify(dispatcher, times(1)).forward(req, resp);
  }

}
