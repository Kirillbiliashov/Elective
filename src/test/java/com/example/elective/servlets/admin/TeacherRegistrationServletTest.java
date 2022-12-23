package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Role;
import com.example.elective.services.AccountService;
import com.example.elective.services.CourseService;
import com.example.elective.services.RoleService;
import com.example.elective.services.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
  private RoleService roleService;

  @Mock
  private RequestDispatcher dispatcher;
  private final static String ACCOUNT_SERVICE_NAME = "accountService";
  private final static String ROLE_SERVICE_NAME = "roleService";
  private final static String REDIRECT_URL = "/elective/admin/teachers?lang=en";

  @BeforeEach()
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(config.getServletContext()).thenReturn(context);
    when(context.getAttribute(ACCOUNT_SERVICE_NAME)).thenReturn(accService);
    when(context.getAttribute(ROLE_SERVICE_NAME)).thenReturn(roleService);
    servlet.init(config);
    mockRequestParams();
  }

  @Test
  void testTeacherRegistrationForm() throws Exception {
    final String ROLE_NAME = "Teacher";
    Role teacherRole = new Role(3, ROLE_NAME);
    when(roleService.getByName(ROLE_NAME)).thenReturn(Optional.of(teacherRole));
    when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    servlet.doGet(req, resp);
    verify(dispatcher, times(1)).forward(req, resp);
  }

  @Test
  void testTeacherRegistrationFormNegative() throws Exception {
    Mockito.doThrow(ServiceException.class).when(roleService).getByName(anyString());
    when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    servlet.doGet(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

  @Test
  void testRegisterTeacher() throws IOException {
    servlet.doPost(req, resp);
    verify(resp, times(1)).sendRedirect(REDIRECT_URL);
  }

  @Test
  void testRegisterTeacherNegative() throws Exception {
    Mockito.doThrow(ServiceException.class).when(accService).save(any(Account.class));
    servlet.doPost(req, resp);
    verify(resp, times(1))
        .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  }

  private void mockRequestParams() {
    final String teacherRoleIdStr = "3";
    when(req.getParameter("roleId")).thenReturn(teacherRoleIdStr);
  }

}
