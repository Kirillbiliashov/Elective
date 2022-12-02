package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.requestMappers.TeacherRequestMapper;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/teachers/register")
public class TeacherRegistrationServlet extends HttpServlet {

  private Mapper<HttpServletRequest, Account> teacherMapper =
      new TeacherRequestMapper();
  private AccountService accService = new AccountService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Account acc = teacherMapper.map(req);
    try {
      accService.save(acc);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect("/elective/admin/teachers");
  }

}
