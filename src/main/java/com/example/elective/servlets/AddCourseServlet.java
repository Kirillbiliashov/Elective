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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/courses/add")
public class AddCourseServlet extends HttpServlet {

  private RequestMapper<Course> courseMapper = new CourseRequestMapper();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Topic> topics = TopicDAO.getAll();
    List<Account> teachers = AccountDAO.getByRole("Teacher");
    req.setAttribute("topics", topics);
    req.setAttribute("teachers", teachers);
    req.getRequestDispatcher("/add-course.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Course course = courseMapper.map(req);
    CourseDAO.save(course);
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
