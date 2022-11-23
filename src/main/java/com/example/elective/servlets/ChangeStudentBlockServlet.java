package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/students/changeBlock/*")
public class ChangeStudentBlockServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    Optional<Account> optAcc = AccountDAO.getById(id);
    if (optAcc.isPresent()) {
      Account acc = optAcc.get();
      acc.setBlocked(!acc.isBlocked());
      AccountDAO.update(acc);
    }
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
