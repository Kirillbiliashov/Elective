package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.TopicDAO;
import com.example.elective.mappers.CourseRequestMapper;
import com.example.elective.mappers.RequestMapper;
import com.example.elective.models.Account;
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

@WebServlet("/courses/add")
public class AddCourseServlet extends HttpServlet {

  private RequestMapper<Course> courseMapper = new CourseRequestMapper();
  private AccountService accService = new AccountService();
  private CourseService courseService = new CourseService();
  private TopicService topicService = new TopicService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Topic> topics = topicService.getAll();
    req.setAttribute("topics", topics);
    req.setAttribute("teachers", accService.getByRole("Teacher"));
    req.getRequestDispatcher("/add-course.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Course course = courseMapper.map(req);
    courseService.save(course);
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
