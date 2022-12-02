package com.example.elective.servlets.admin;

import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.AccountService;
import com.example.elective.services.StudentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/students/changeBlock/*")
public class ChangeStudentBlockServlet extends HttpServlet {

  private StudentService studentService = new StudentService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    try {
      studentService.changeBlockStatus(id);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
