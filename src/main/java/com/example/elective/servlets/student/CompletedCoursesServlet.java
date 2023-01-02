package com.example.elective.servlets.student;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.CourseService;
import com.example.elective.services.StudentService;
import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/completed_courses")
public class CompletedCoursesServlet extends HttpServlet {

  private CourseService courseService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute("courseService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int studentId = RequestUtils.getCurrentUserId(req);
    try {
      req.setAttribute("completedCourses",
          courseService.getCompletedCourses(studentId));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/student-completed-courses.jsp").forward(req, resp);
  }

}
