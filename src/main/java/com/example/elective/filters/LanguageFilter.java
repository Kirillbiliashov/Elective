package com.example.elective.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    String lang = req.getParameter("lang");
    if (lang != null) {
      String url = req.getRequestURI();
      HttpSession session = req.getSession();
      session.setAttribute("lang", lang);
      res.sendRedirect(url);
      return;
    }
    chain.doFilter(req, res);
  }
}
