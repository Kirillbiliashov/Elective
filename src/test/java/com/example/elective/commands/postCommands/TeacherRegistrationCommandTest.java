package com.example.elective.commands.postCommands;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
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

import static com.example.elective.commands.postCommands.TeacherRegistrationPostCommand.REDIRECT_URL;
import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeacherRegistrationCommandTest {

  private final TeacherRegistrationPostCommand command =
      new TeacherRegistrationPostCommand();
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
  void testRegisterTeacher() throws Exception {
    command.process();
    verify(accService, times(1)).save(any(Account.class));
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testRegisterTeacherNegative() throws Exception {
    Mockito.doThrow(ServiceException.class).when(accService).save(any(Account.class));
    command.process();
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}