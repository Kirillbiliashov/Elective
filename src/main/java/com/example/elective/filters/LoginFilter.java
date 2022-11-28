package com.example.elective.filters;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "loginPath", value = "/login"),
    @WebInitParam(name = "signupPath", value = "/signup")})
public class LoginFilter extends HttpFilter {

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
    boolean isLoginPath = req.getServletPath().equals(loginPath);
    boolean isSignupPath = req.getServletPath().equals(signupPath);
    boolean isLoggedIn = session.getAttribute("account") != null;
    if (!(isLoggedIn || isLoginPath || isSignupPath)) {
      res.sendRedirect("/elective/login");
    } else {
      chain.doFilter(req, res);
    }
  }

}
