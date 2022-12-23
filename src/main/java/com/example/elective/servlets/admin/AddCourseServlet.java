package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Course;
import com.example.elective.models.Topic;
import com.example.elective.services.AccountService;
import com.example.elective.services.CourseService;
import com.example.elective.services.TopicService;
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
import java.util.List;

@WebServlet("/admin/courses/add")
public class AddCourseServlet extends HttpServlet {

  private RequestMapper<Course> courseMapper = new CourseRequestMapper();
  private TopicService topicService;
  private AccountService accService;
  private CourseService courseService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    topicService = (TopicService) context.getAttribute("topicService");
    accService = (AccountService) context.getAttribute("accountService");
    courseService = (CourseService) context.getAttribute("courseService");
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      List<Topic> topics = topicService.getAll();
      req.setAttribute("topics", topics);
      req.setAttribute("teachers", accService.getByRole("Teacher"));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/add-course.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Course course = courseMapper.map(req);
    try {
      courseService.save(course);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(RequestUtils.getRedirectUrl(req, Constants.ADMIN_SERVLET_NAME));
  }

}
