package com.example.elective.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.elective.utils.Constants.HOME_URL_ATTR;

public class AuthorizationFilter extends HttpFilter {

  private static final String PATH_SEPARATOR = "/";
  private static final int ROLE_DOMAIN_IDX = 1;

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res,
                          FilterChain chain) throws IOException, ServletException {
    HttpSession session = req.getSession();
    String url = req.getServletPath();
    String homeUrl = (String) session.getAttribute(HOME_URL_ATTR);
    if (isValidUrl(url, homeUrl)) chain.doFilter(req, res);
    else res.sendError(HttpServletResponse.SC_FORBIDDEN);
  }

  private boolean isValidUrl(String url, String homeUrl) {
    return url.split(PATH_SEPARATOR)[ROLE_DOMAIN_IDX].equals(homeUrl);
  }

}
