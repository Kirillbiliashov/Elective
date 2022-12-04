package com.example.elective.servlets.admin;

import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.requestMappers.CourseRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Course;
import com.example.elective.models.Topic;
import com.example.elective.services.AccountService;
import com.example.elective.services.CourseService;
import com.example.elective.services.TopicService;

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
  private AccountService accService = new AccountService();
  private CourseService courseService = new CourseService();
  private TopicService topicService = new TopicService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
