package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;
import com.example.elective.services.AccountService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/students/changeBlock/*")
public class ChangeStudentBlockServlet extends HttpServlet {

  private AccountService accService = new AccountService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    accService.changeBlockStatus(id);
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
