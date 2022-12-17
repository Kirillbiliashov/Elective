package com.example.elective.servlets.student;

import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.StudentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/registered_courses")
public class RegisteredCoursesServlet extends HttpServlet {

  private StudentService studentService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    studentService = (StudentService) context.getAttribute("studentService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int studentId = Utils.getCurrentUserId(req);
    try {
      req.setAttribute("registeredCourses",
          studentService.getRegisteredCoursesMap(studentId));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/student-registered-courses.jsp").forward(req, resp);
  }

}
