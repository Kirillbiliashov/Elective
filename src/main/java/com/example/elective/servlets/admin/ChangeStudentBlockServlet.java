package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.StudentService;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.RequestUtils.getIdFromPathInfo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet class that handles POST request for mapping "/admin/students/changeBlock/*"
 * @author Kirill Biliashov
 */

@WebServlet("/admin/students/changeBlock/*")
public class ChangeStudentBlockServlet extends HttpServlet {

  private static final String JSP_PAGE = ADMIN_URL + "/students";
  private StudentService studentService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    studentService = (StudentService) context.getAttribute(STUDENT_SERVICE);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int id = getIdFromPathInfo(req.getPathInfo());
    try {
      studentService.changeBlockStatus(id);
      resp.sendRedirect(JSP_PAGE);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
