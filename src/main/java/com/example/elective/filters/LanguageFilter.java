package com.example.elective.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.elective.utils.Constants.LANG_ATTR;

public class LanguageFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    String lang = req.getParameter(LANG_ATTR);
    if (lang != null) {
      String url = req.getRequestURI();
      HttpSession session = req.getSession();
      session.setAttribute(LANG_ATTR, lang);
      res.sendRedirect(url);
      return;
    }
    chain.doFilter(req, res);
  }

}
