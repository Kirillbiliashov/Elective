package com.example.elective.filters;

import com.example.elective.filters.AuthorizationFilter;
import com.example.elective.filters.LanguageFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LanguageFilterTest {

  private LanguageFilter langFilter = new LanguageFilter();

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse resp;

  @Mock
  private FilterChain chain;

  @BeforeEach
  void beforeAll() {
    MockitoAnnotations.openMocks(this);
  }

  @ParameterizedTest
  @ValueSource(strings = {"en", "ru"})
  void testPresentParameter(String lang) throws ServletException, IOException {
    when(req.getParameter("lang")).thenReturn(lang);
    langFilter.doFilter(req, resp, chain);
    verify(chain, times(1)).doFilter(req, resp);
  }

  @Test
  void testAbsentParameter() throws ServletException, IOException {
    when(req.getParameter("lang")).thenReturn(null);
    langFilter.doFilter(req, resp, chain);
    verify(resp, times(1)).sendRedirect(req.getRequestURL() +  "?lang=en");
  }

}
