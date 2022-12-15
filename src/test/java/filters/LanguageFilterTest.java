package filters;

import com.example.elective.filters.AuthorizationFilter;
import com.example.elective.filters.LanguageFilter;
import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LanguageFilterTest {

  private LanguageFilter langFilter = new LanguageFilter();
  private HttpServletRequest req = mock(HttpServletRequest.class);
  private HttpServletResponse resp = mock(HttpServletResponse.class);
  private FilterChain chain = mock(FilterChain.class);

  @Test
  void testPresentParameter() throws ServletException, IOException {
    when(req.getParameter("lang")).thenReturn("ru");
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
