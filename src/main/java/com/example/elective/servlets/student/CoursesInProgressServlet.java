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

import static com.example.elective.utils.Constants.COURSES_IN_PROGRESS_ATTR;
import static com.example.elective.utils.Constants.COURSE_SERVICE;
import static com.example.elective.utils.RequestUtils.getCurrentUserId;

/**
 * Servlet class that handles GET request url for mapping "/student/courses_in_progress"
 * @author Kirill Biliashov
 */

@WebServlet("/student/courses_in_progress")
public class CoursesInProgressServlet extends HttpServlet {

  private static final String JSP_PAGE = "/student-courses-in-progress.jsp";
  private CourseService courseService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute(COURSE_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int studentId = getCurrentUserId(req);
    try {
      req.setAttribute(COURSES_IN_PROGRESS_ATTR,
          courseService.getCoursesInProgress(studentId));
      req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
