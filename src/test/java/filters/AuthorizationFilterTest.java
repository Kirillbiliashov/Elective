package filters;

import com.example.elective.filters.AuthenticationFilter;
import com.example.elective.filters.AuthorizationFilter;
import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AuthorizationFilterTest {

  private AuthorizationFilter authFilter = new AuthorizationFilter();
  private HttpServletRequest req = mock(HttpServletRequest.class);
  private HttpServletResponse resp = mock(HttpServletResponse.class);
  private HttpSession session = mock(HttpSession.class);
  private FilterChain chain = mock(FilterChain.class);

  @Test
  void testAuthorized() throws ServletException, IOException {
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute("homeUrl")).thenReturn("/elective/admin");
    when(req.getServletPath()).thenReturn("/elective/admin/teachers");
    when(req.getQueryString()).thenReturn("lang=en");
    authFilter.doFilter(req, resp, chain);
    verify(chain, times(1)).doFilter(req, resp);
  }

  @Test
  void testUnauthorized() throws ServletException, IOException {
    when(req.getSession()).thenReturn(session);
    when(session.getAttribute("homeUrl")).thenReturn("/elective/admin");
    when(req.getServletPath()).thenReturn("/elective/teacher");
    when(req.getQueryString()).thenReturn("lang=en&page=2");
    authFilter.doFilter(req, resp, chain);
    verify(resp, times(1)).sendError(403);
  }
}
