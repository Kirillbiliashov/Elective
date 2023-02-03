package com.example.elective.commands.postCommands;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Blocklist;
import com.example.elective.services.impl.AccountServiceImpl;
import com.example.elective.services.impl.BlocklistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.example.elective.commands.postCommands.LoginPostCommand.*;
import static com.example.elective.utils.Constants.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginCommandTest {

  private final LoginPostCommand command = new LoginPostCommand();
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;
  @Mock
  private AccountServiceImpl accountService;
  @Mock
  private BlocklistServiceImpl studentService;
  @Mock
  private Account acc;
  @Mock
  private HttpSession session;
  @Mock
  private RequestDispatcher reqDispatcher;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(context.getAttribute(ACCOUNT_SERVICE)).thenReturn(accountService);
    when(context.getAttribute(BLOCKLIST_SERVICE)).thenReturn(studentService);
    when(req.getRequestDispatcher(anyString())).thenReturn(reqDispatcher);
  }

  @Test
  void testSuccessfulLogin() throws Exception {
    when(req.getSession()).thenReturn(session);
    configureValidCredentialsReturn();
    when(studentService.getBlockStatus(acc.getId())).thenReturn(Optional.empty());
    when(accountService.findByCredentials(anyString(), anyString()))
        .thenReturn(Optional.of(acc));
    command.init(context, req, resp);
    command.process();
    verify(session, times(1)).setAttribute(ACCOUNT_ATTR, acc);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  private void configureValidCredentialsReturn() {
    when(req.getParameter(LOGIN_PARAM)).thenReturn("valid_login");
    when(req.getParameter(PASSWORD_PARAM)).thenReturn("valid_password");
  }

  @Test
  void testAbsentAccount() throws Exception {
    when(req.getRequestDispatcher(JSP_PAGE)).thenReturn(reqDispatcher);
    when(req.getParameter(LOGIN_PARAM)).thenReturn("invalid_login");
    when(req.getParameter(PASSWORD_PARAM)).thenReturn("invalid_password");
    when(accountService.findByCredentials(anyString(), anyString()))
        .thenReturn(Optional.empty());
    command.init(context, req, resp);
    command.process();
    verify(req, times(1)).setAttribute("loginFailed", true);
    verify(reqDispatcher, times(1)).forward(req, resp);
  }

}