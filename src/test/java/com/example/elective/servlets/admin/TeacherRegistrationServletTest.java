package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.example.elective.servlets.admin.TeacherRegistrationServlet.REDIRECT_URL;
import static com.example.elective.utils.Constants.ACCOUNT_SERVICE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TeacherRegistrationServletTest {

  private TeacherRegistrationServlet servlet = new TeacherRegistrationServlet();

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse resp;

  @Mock
  private ServletConfig config;

  @Mock
  private ServletContext context;

  @Mock
  private AccountService accService;

  @Mock
  private RequestDispatcher dispatcher;

  @BeforeEach()
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(config.getServletContext()).thenReturn(context);
    when(context.getAttribute(ACCOUNT_SERVICE)).thenReturn(accService);
    servlet.init(config);
  }

  @Test
  void testTeacherRegistrationForm() throws Exception {
    when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    servlet.doGet(req, resp);
    verify(dispatcher, times(1)).forward(req, resp);
  }

  @Test
  void testTeacherRegistrationFormNegative() throws Exception {
    Mockito.doThrow(ServiceException.class).when(accService).getLogins();
    when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    servlet.doGet(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

  @Test
  void testRegisterTeacher() throws Exception {
    servlet.doPost(req, resp);
    verify(accService, times(1)).save(any(Account.class));
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testRegisterTeacherNegative() throws Exception {
    Mockito.doThrow(ServiceException.class).when(accService).save(any(Account.class));
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

}
