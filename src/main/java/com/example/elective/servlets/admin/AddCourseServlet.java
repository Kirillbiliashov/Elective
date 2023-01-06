package com.example.elective.servlets.admin;

import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Course;
import com.example.elective.models.Topic;
import com.example.elective.services.AccountService;
import com.example.elective.services.CourseService;
import com.example.elective.services.TopicService;
import static com.example.elective.utils.Constants.*;
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

  private static final String JSP_PAGE = "/add-course.jsp";
  private final RequestMapper<Course> courseMapper = new CourseRequestMapper();
  private TopicService topicService;
  private AccountService accService;
  private CourseService courseService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    topicService = (TopicService) context.getAttribute(TOPIC_SERVICE);
    accService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
    courseService = (CourseService) context.getAttribute(COURSE_SERVICE);
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      List<Topic> topics = topicService.getAll();
      req.setAttribute(TOPICS_ATTR, topics);
      req.setAttribute(TEACHERS_ATTR, accService.getByRole(TEACHER_ROLE));
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
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
    resp.sendRedirect(ADMIN_SERVLET_NAME);
  }

}
