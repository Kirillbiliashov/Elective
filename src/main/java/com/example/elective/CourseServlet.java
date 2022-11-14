package com.example.elective;

import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.TopicDAO;
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

@WebServlet("/add_course")
public class CourseServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Topic> topics = TopicDAO.getAll();
    req.setAttribute("topics", topics);
    req.getRequestDispatcher("new-course-form.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Course course = mapRequestToCourse(req);
    CourseDAO.save(course);
    resp.sendRedirect("admin");

  }

  private Course mapRequestToCourse(HttpServletRequest req) {
    return new Course()
        .setName(req.getParameter("name"))
        .setDuration(Integer.parseInt(req.getParameter("duration")))
        .setStartDate(Date.valueOf(req.getParameter("startDate")))
        .setTopicId(Integer.parseInt(req.getParameter("topicId")));
  }
}
