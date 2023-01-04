package com.example.elective.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/toggleLanguage")
public class ToggleLanguageServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("inside toggle servlet");
    HttpSession session = req.getSession();
    session.setAttribute("lang", req.getParameter("lang"));
    System.out.println("redirect to " + req.getParameter("redirectUrl"));
    resp.sendRedirect(req.getParameter("redirectUrl"));
  }

}
