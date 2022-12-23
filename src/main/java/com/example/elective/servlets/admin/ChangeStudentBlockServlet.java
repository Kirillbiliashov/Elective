package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.StudentService;
import com.example.elective.utils.Constants;
import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/students/changeBlock/*")
public class ChangeStudentBlockServlet extends HttpServlet {

  private StudentService studentService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    studentService = (StudentService) context.getAttribute("studentService");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int id = RequestUtils.getIdFromPathInfo(req.getPathInfo());
    try {
      studentService.changeBlockStatus(id);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(RequestUtils.getRedirectUrl(req, Constants.ADMIN_SERVLET_NAME));
  }

}
