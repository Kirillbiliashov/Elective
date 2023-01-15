package com.example.elective.commands.getCommands;

import com.example.elective.commands.getCommands.MainCommand;
import com.example.elective.models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;
import static org.mockito.Mockito.*;

public class MainCommandTest {

  private final static MainCommand command = new MainCommand();
  @Mock
  private HttpSession session;
  @Mock
  private HttpServletRequest req;
  @Mock
  private HttpServletResponse resp;
  @Mock
  private ServletContext context;
  @Mock
  private Account account;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @ParameterizedTest
  @ValueSource(strings = {"Student", "Teacher", "Admin"})
  void testMainCommand(String roleName) throws Exception {
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute(ACCOUNT_ATTR)).thenReturn(account);
    when(account.getRole()).thenReturn(roleName);
    command.init(context, req, resp);
    command.process();
    String redirectUrl = roleName.toLowerCase();
    verify(resp, times(1)).sendRedirect(redirectUrl);
  }

}

