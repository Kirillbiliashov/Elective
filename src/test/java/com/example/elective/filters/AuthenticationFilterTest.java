package com.example.elective.filters;


import com.example.elective.filters.AuthenticationFilter;
import com.example.elective.models.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.elective.TestConstants.ACCOUNT_ATTR_NAME;
import static com.example.elective.TestConstants.HOME_URL_ATTR_NAME;
import static org.mockito.Mockito.*;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Stream;

public class AuthenticationFilterTest {

  private static final String LOGIN_PATH = "/login";
  private static final String SIGNUP_PATH = "/signup";
  private static final String REDIRECT_URL = "/elective/login";
  private final AuthenticationFilter authFilter = new AuthenticationFilter();
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private HttpSession session;
  @Mock
  private FilterChain chain;
  @Mock
  private FilterConfig filterConfig;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(filterConfig.getInitParameter("loginPath")).thenReturn(LOGIN_PATH);
    when(filterConfig.getInitParameter("signupPath")).thenReturn(SIGNUP_PATH);
    authFilter.init(filterConfig);
    when(req.getSession()).thenReturn(session);
  }

  @ParameterizedTest
  @ValueSource(strings = {"/some/non_login/path", "/elective/logout", "/elective/teacher?page=1"})
  void testUnauthenticated(String servletUrl) throws ServletException, IOException {
    when(session.getAttribute(anyString())).thenReturn(null);
    when(req.getServletPath()).thenReturn(servletUrl);
    authFilter.doFilter(req, resp, chain);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @ParameterizedTest
  @ValueSource(strings = {LOGIN_PATH, SIGNUP_PATH})
  void testUnauthenticatedAuthReq(String authPath) throws Exception {
    when(session.getAttribute(ACCOUNT_ATTR_NAME)).thenReturn(null);
    when(req.getServletPath()).thenReturn(authPath);
    authFilter.doFilter(req, resp, chain);
    verify(chain, times(1)).doFilter(req, resp);
  }

  @Test
  void testAuthenticated() throws Exception {
    when(session.getAttribute(ACCOUNT_ATTR_NAME)).thenReturn(mock(Account.class));
    when(req.getServletPath()).thenReturn("/some/non_login/path");
    authFilter.doFilter(req, resp, chain);
    verify(chain, times(1)).doFilter(req, resp);
  }

  @ParameterizedTest
  @ValueSource(strings = {LOGIN_PATH, SIGNUP_PATH})
  void testAuthenticatedAuthReq(String servletUrl) throws Exception {
    when(session.getAttribute(ACCOUNT_ATTR_NAME)).thenReturn(mock(Account.class));
    when(req.getServletPath()).thenReturn(servletUrl);
    authFilter.doFilter(req, resp, chain);
    String redirectUrl = (String) session.getAttribute(HOME_URL_ATTR_NAME);
    verify(resp, times(1)).sendRedirect(redirectUrl);
  }

}

