package com.example.elective.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res,
                          FilterChain chain) throws IOException, ServletException {
    HttpSession session = req.getSession();
    String homeUrl = (String) session.getAttribute("homeUrl");
    if (req.getServletPath().contains(homeUrl)) {
      chain.doFilter(req, res);
    } else {
      res.sendError(403);
    }
  }

}
