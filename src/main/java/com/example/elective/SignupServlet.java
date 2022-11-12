package com.example.elective;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.StudentDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("signup-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Account acc = mapRequestToAccount(req);
    AccountDAO.save(acc);
    Student student = mapRequestToStudent(req);
    student.setAccountId(acc.getId());
    StudentDAO.save(student);
    resp.sendRedirect("/elective/login");
  }

  private Account mapRequestToAccount(HttpServletRequest req) {
    Account acc = new Account();
    acc.setLogin(req.getParameter("login"));
    acc.setPassword(req.getParameter("password"));
    return acc;
  }

  private Student mapRequestToStudent(HttpServletRequest req) {
    Student student = new Student();
    student.setFirstName(req.getParameter("firstName"));
    student.setLastName(req.getParameter("lastName"));
    return student;
  }

}
