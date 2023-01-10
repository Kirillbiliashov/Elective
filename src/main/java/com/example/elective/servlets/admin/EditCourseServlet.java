package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Course;
import com.example.elective.services.CourseService;
import com.example.elective.services.TeacherService;
import com.example.elective.services.TopicService;

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
import java.util.Optional;

/**
 * Servlet class that handles POST request for mapping "/admin/courses/edit/*"
 * @author Kirill Biliashov
 */

@WebServlet("/admin/courses/edit/*")
public class EditCourseServlet extends HttpServlet {

  private static final String JSP_PAGE = "/edit-course.jsp";
  private final RequestMapper<Course> courseMapper = new CourseRequestMapper();
  private CourseService courseService;
  private TopicService topicService;
  private TeacherService teacherService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute(COURSE_SERVICE);
    topicService = (TopicService) context.getAttribute(TOPIC_SERVICE);
    teacherService = (TeacherService) context.getAttribute(TEACHER_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int id = getIdFromPathInfo(req.getPathInfo());
    try {
      Optional<Course> optCourse = courseService.findById(id);
      if (!optCourse.isPresent()) {
        resp.sendRedirect(ADMIN_URL);
        return;
      }
      req.setAttribute(COURSE_ATTR, optCourse.get());
      req.setAttribute(TOPICS_ATTR, topicService.getAll());
      req.setAttribute(TEACHERS_ATTR, teacherService.getAll());
      req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    try {
      courseService.update(courseMapper.map(req));
      resp.sendRedirect(ADMIN_URL);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
