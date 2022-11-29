package com.example.elective.servlets.admin;

import com.example.elective.dao.AccountDAO;
import com.example.elective.services.AccountService;
import com.example.elective.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/students")
public class StudentsServlet extends HttpServlet {

  private StudentService studentService = new StudentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("students", studentService.getAll());
    req.getRequestDispatcher("/admin-students.jsp").forward(req, resp);
  }

}
