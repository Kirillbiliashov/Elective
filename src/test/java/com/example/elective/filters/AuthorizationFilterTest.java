package com.example.elective.filters;

import com.example.elective.filters.AuthenticationFilter;
import com.example.elective.filters.AuthorizationFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AuthorizationFilterTest {

  private AuthorizationFilter authFilter = new AuthorizationFilter();

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse resp;

  @Mock
  private HttpSession session;

  @Mock
  private FilterChain chain;


  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  static Stream<Arguments> authorizedUrlSet() {
    return Stream.of(
        Arguments.of((Object) new String[]{"/elective/admin",
            "/elective/admin/teachers"}),
        Arguments.of((Object) new String[]{"/elective/student",
            "/elective/student/registered_courses"}),
        Arguments.of((Object) new String[]{"/elective/teacher",
            "/elective/teacher"})
    );
  }

  static Stream<Arguments> unauthorizedUrlSet() {
    return Stream.of(
        Arguments.of((Object) new String[]{"/elective/admin",
            "/elective/teacher"}),
        Arguments.of((Object) new String[]{"/elective/teacher",
            "/elective/student/registered_courses"}),
        Arguments.of((Object) new String[]{"/elective/student",
            "/elective/admin/courses/edit/1"})
    );
  }

  @ParameterizedTest
  @MethodSource("authorizedUrlSet")
  void testAuthorized(String[] urls) throws ServletException, IOException {
    String homeUrl = urls[0];
    String servletUrl = urls[1];
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute("homeUrl")).thenReturn(homeUrl);
    when(req.getServletPath()).thenReturn(servletUrl);
    when(req.getQueryString()).thenReturn("lang=en");
    authFilter.doFilter(req, resp, chain);
    verify(chain, times(1)).doFilter(req, resp);
  }

  @ParameterizedTest
  @MethodSource("unauthorizedUrlSet")
  void testUnauthorized(String[] urls) throws ServletException, IOException {
    String homeUrl = urls[0];
    String servletUrl = urls[1];
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute("homeUrl")).thenReturn(homeUrl);
    when(req.getServletPath()).thenReturn(servletUrl);
    when(req.getQueryString()).thenReturn("lang=en");
    authFilter.doFilter(req, resp, chain);
    verify(resp, times(1)).sendError(403);
  }

}
