package com.example.elective;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("courses", CourseDAO.getAll());
    req.setAttribute("students", AccountDAO.getByRole("Student"));
    req.getRequestDispatcher("admin.jsp").forward(req, resp);
  }

}
