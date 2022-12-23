package com.example.elective.servlets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class LogoutServletTest {

  private static final LogoutServlet servlet = new LogoutServlet();

  @Mock
  private HttpSession session;

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse resp;
  private static final String REDIRECT_URL = "/elective/login?lang=en";

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testLogout() throws Exception {
    when(req.getSession()).thenReturn(session);
    servlet.doGet(req, resp);
    verify(session, times(1)).invalidate();
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }
}
