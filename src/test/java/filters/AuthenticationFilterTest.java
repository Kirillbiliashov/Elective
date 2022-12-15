package filters;


import com.example.elective.filters.AuthenticationFilter;
import com.example.elective.models.Account;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilterTest {

  private AuthenticationFilter authFilter = new AuthenticationFilter();
  private HttpServletRequest req = mock(HttpServletRequest.class);
  private HttpServletResponse resp = mock(HttpServletResponse.class);
  private HttpSession session = mock(HttpSession.class);
  private FilterChain chain = mock(FilterChain.class);
  private final static String LOGIN_PATH = "/elective/login";


  @Test
  void testUnauthenticated() throws ServletException, IOException {
    when(session.getAttribute(anyString())).thenReturn(null);
    when(req.getServletPath()).thenReturn("/some/non_login/path");
    when(req.getSession()).thenReturn(session);
    authFilter.doFilter(req, resp, chain);
    verify(resp, times(1)).sendRedirect(LOGIN_PATH);
  }

  @Test
  void testUnauthenticatedLoginReq() throws ServletException, IOException {
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute("account")).thenReturn(null);
    when(req.getServletPath()).thenReturn(LOGIN_PATH);
    authFilter.doFilter(req, resp, chain);
    verify(chain, times(1)).doFilter(req, resp);
  }

  @Test
  void testAuthenticated() throws ServletException, IOException {
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute("account")).thenReturn(mock(Account.class));
    when(req.getServletPath()).thenReturn("/some/non_login/path");
    authFilter.doFilter(req, resp, chain);
    verify(chain, times(1)).doFilter(req, resp);
  }

  @Test
  void testAuthenticatedLoginReq() throws ServletException, IOException {
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute("account")).thenReturn(mock(Account.class));
    when(req.getServletPath()).thenReturn(LOGIN_PATH);
    authFilter.doFilter(req, resp, chain);
    verify(resp, times(1)).sendRedirect((String) session.getAttribute("homeUrl"));
  }

}

