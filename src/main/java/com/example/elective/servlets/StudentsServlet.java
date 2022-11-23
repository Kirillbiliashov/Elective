package com.example.elective.servlets;

import com.example.elective.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/students")
public class StudentsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("students", AccountDAO.getByRole("Student"));
    req.getRequestDispatcher("/admin-students.jsp").forward(req, resp);
  }

}
