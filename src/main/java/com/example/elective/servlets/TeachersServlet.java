package com.example.elective.servlets;

import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/teachers")
public class TeachersServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("teachers", AccountDAO.getByRole("Teacher"));
    req.getRequestDispatcher("/admin-teachers.jsp").forward(req, resp);
  }

}
