package com.example.elective.filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

public class AuthenticationFilter extends HttpFilter {

  private String loginPath;
  private String signupPath;

  @Override
  public void init(FilterConfig config) {
    loginPath = config.getInitParameter("loginPath");
    signupPath = config.getInitParameter("signupPath");
  }

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res,
                          FilterChain chain) throws IOException, ServletException {
    HttpSession session = req.getSession();
    String servletPath = req.getServletPath();
    boolean isLoginPath = servletPath.equals(loginPath);
    boolean isSignupPath = servletPath.equals(signupPath);
    boolean isLoggedIn = session.getAttribute(ACCOUNT_ATTR) != null;
    boolean isAuthPath = isLoginPath || isSignupPath;
    if (!isAuthPath && !isLoggedIn) {
      res.sendRedirect(LOGIN_URL);
      return;
    } else if (isLoggedIn && isAuthPath) {
      res.sendRedirect((String) session.getAttribute(HOME_URL_ATTR));
      return;
    }
    chain.doFilter(req, res);
  }

}
