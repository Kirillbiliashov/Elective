package com.example.elective.servlets;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;
import com.example.elective.utils.Constants;
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

import static com.example.elective.servlets.LoginServlet.*;
import static com.example.elective.utils.Constants.ACCOUNT_ATTR;
import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServletTest {

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
    when(context.getAttribute(ACCOUNT_SERVICE)).thenReturn(accountService);
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
    verify(session, times(1)).setAttribute(ACCOUNT_ATTR, acc);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testBlockedAccount() throws Exception {
    when(req.getRequestDispatcher(JSP_PAGE)).thenReturn(reqDispatcher);
    configureValidCredentialsReturn();
    when(acc.isBlocked()).thenReturn(true);
    when(accountService.findByCredentials(anyString(), anyString()))
        .thenReturn(Optional.of(acc));
    servlet.doPost(req, resp);
    verify(req, times(1)).setAttribute("accountBlocked", true);
    verify(reqDispatcher, times(1)).forward(req, resp);
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
    servlet.doPost(req, resp);
    verify(req, times(1)).setAttribute("loginFailed", true);
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
