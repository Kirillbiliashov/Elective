package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.RoleDAO;
import com.example.elective.mappers.AccountRequestMapper;
import com.example.elective.mappers.RequestMapper;
import com.example.elective.mappers.TeacherRequestMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/teachers/register")
public class TeacherRegistrationServlet extends HttpServlet {

  private RequestMapper<Account> teacherMapper = new TeacherRequestMapper();
  private AccountService accService = new AccountService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Account acc = teacherMapper.map(req);
    accService.save(acc);
    resp.sendRedirect("/elective/admin/teachers");
  }

}
