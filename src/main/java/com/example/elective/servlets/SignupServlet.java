package com.example.elective.servlets;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.RoleDAO;
import com.example.elective.mappers.RequestMapper;
import com.example.elective.mappers.StudentRequestMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

  private RequestMapper<Account> studentMapper = new StudentRequestMapper();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Account acc = studentMapper.map(req);
    AccountDAO.save(acc);
    resp.sendRedirect("login");
  }

}
