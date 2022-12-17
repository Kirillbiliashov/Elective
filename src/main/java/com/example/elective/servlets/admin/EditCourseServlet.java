package com.example.elective.servlets.admin;

import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Course;
import com.example.elective.services.CourseService;
import com.example.elective.services.TeacherService;
import com.example.elective.services.TopicService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/courses/edit/*")
public class EditCourseServlet extends HttpServlet {

  private CourseService courseService;
  private TopicService topicService;
  private TeacherService teacherService;

  private RequestMapper<Course> courseMapper = new CourseRequestMapper();

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute("courseService");
    topicService = (TopicService) context.getAttribute("topicService");
    teacherService = (TeacherService) context.getAttribute("teacherService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    try {
      Optional<Course> optCourse = courseService.getById(id);
      if (!optCourse.isPresent()) {
        resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
        return;
      }
      req.setAttribute("course", optCourse.get());
      req.setAttribute("topics", topicService.getAll());
      req.setAttribute("teachers", teacherService.getAll());
      req.getRequestDispatcher("/edit-course.jsp").forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      courseService.update(courseMapper.map(req));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(Utils.getRedirectUrl(req, Utils.ADMIN_SERVLET_NAME));
  }

}
