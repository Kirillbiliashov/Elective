package com.example.elective.servlets.student;

import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/courses_in_progress")
public class CoursesInProgressServlet extends HttpServlet {

  private StudentService studentService = new StudentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int studentId = Utils.getCurrentUserId(req);
    try {
      req.setAttribute("coursesInProgress",
          studentService.getCoursesInProgress(studentId));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/student-courses-in-progress.jsp").forward(req, resp);
  }

}
