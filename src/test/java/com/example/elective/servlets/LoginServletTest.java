package com.example.elective.servlets;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import static com.example.elective.TestConstants.ACCOUNT_SERVICE_NAME;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServletTest {

  private static final String REDIRECT_URL = "main?lang=en";
  private static final String LOGIN_JSP_PAGE = "login-form.jsp";
  private static final String BLOCKED_ACC_MSG = "Your account is blocked";
  private static final String INVALID_CREDENTIALS_MSG = "Login or password is incorrect";
  private static final String ERROR_ATTR = "errorMsg";
  private static final String LOGIN_PARAM_NAME = "login";
  private static final String PASSWORD_PARAM_NAME = "password";
  private final LoginServlet servlet = new LoginServlet();
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletConfig config;
  @Mock
  private ServletContext context;
  @Mock
  private AccountService accountService;
  @Mock
  private Account acc;
  @Mock
  private HttpSession session;
  @Mock
  private RequestDispatcher reqDispatcher;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(config.getServletContext()).thenReturn(context);
    when(context.getAttribute(ACCOUNT_SERVICE_NAME)).thenReturn(accountService);
    servlet.init(config);
  }

  @Test
  void testSuccessfulLogin() throws Exception {
    when(req.getSession()).thenReturn(session);
    configureValidCredentialsReturn();
    when(acc.isBlocked()).thenReturn(false);
    when(accountService.findByCredentials(anyString(), anyString()))
        .thenReturn(Optional.of(acc));
    servlet.doPost(req, resp);
    verify(session, times(1)).setAttribute("account", acc);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testBlockedAccount() throws Exception {
    when(req.getRequestDispatcher(LOGIN_JSP_PAGE)).thenReturn(reqDispatcher);
    configureValidCredentialsReturn();
    when(acc.isBlocked()).thenReturn(true);
    when(accountService.findByCredentials(anyString(), anyString()))
        .thenReturn(Optional.of(acc));
    servlet.doPost(req, resp);
    verify(req, times(1)).setAttribute(ERROR_ATTR, BLOCKED_ACC_MSG);
    verify(reqDispatcher, times(1)).forward(req, resp);
  }

  private void configureValidCredentialsReturn() {
    when(req.getParameter(LOGIN_PARAM_NAME)).thenReturn("valid_login");
    when(req.getParameter(PASSWORD_PARAM_NAME)).thenReturn("valid_password");
  }

  @Test
  void testAbsentAccount() throws Exception {
    when(req.getRequestDispatcher(LOGIN_JSP_PAGE)).thenReturn(reqDispatcher);
    when(req.getParameter(LOGIN_PARAM_NAME)).thenReturn("invalid_login");
    when(req.getParameter(PASSWORD_PARAM_NAME)).thenReturn("invalid_password");
    when(accountService.findByCredentials(anyString(), anyString()))
        .thenReturn(Optional.empty());
    servlet.doPost(req, resp);
    verify(req, times(1)).setAttribute(ERROR_ATTR, INVALID_CREDENTIALS_MSG);
    verify(reqDispatcher, times(1)).forward(req, resp);
  }

  @Test
  void testServiceException() throws Exception {
    doThrow(ServiceException.class).when(accountService)
        .findByCredentials(any(), any());
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
