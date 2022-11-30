package com.example.elective.servlets;

import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.requestMappers.StudentRequestMapper;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

  private Mapper<HttpServletRequest, Account> studentMapper =
      new StudentRequestMapper();
  private AccountService accService = new AccountService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Account acc = studentMapper.map(req);
    accService.save(acc);
    resp.sendRedirect("login");
  }

}
