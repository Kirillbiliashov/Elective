package com.example.elective.servlets;

import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.RoleService;
import com.example.elective.servlets.MainServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.example.elective.TestConstants.ACCOUNT_ATTR_NAME;
import static org.mockito.Mockito.*;

public class MainServletTest {

  private final static MainServlet servlet = new MainServlet();
  private static final String ROLE_SERVICE_NAME = "roleService";
  @Mock
  private HttpSession session;
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private RoleService roleService;
  @Mock
  private ServletConfig config;
  @Mock
  private ServletContext context;
  @Mock
  private Account account;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    when(config.getServletContext()).thenReturn(context);
    when(context.getAttribute(ROLE_SERVICE_NAME)).thenReturn(roleService);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Student", "Teacher", "Admin"})
  void testMainServlet(String roleName) throws Exception {
    final int ROLE_ID = 1;
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute(ACCOUNT_ATTR_NAME)).thenReturn(account);
    when(account.getRoleId()).thenReturn(ROLE_ID);
    when(roleService.getById(ROLE_ID))
        .thenReturn(Optional.of(new Role(ROLE_ID, roleName)));
    servlet.init(config);
    servlet.doGet(req, resp);
    String redirectUrl = roleName.toLowerCase() + "?lang=en";
    verify(resp, times(1)).sendRedirect(redirectUrl);
  }

}
