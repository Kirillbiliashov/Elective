package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.TopicDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Topic;
import com.example.elective.services.AccountService;
import com.example.elective.services.CourseService;
import com.example.elective.services.TeacherService;
import com.example.elective.services.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@WebServlet("/courses/edit/*")
public class EditCourseServlet extends HttpServlet {

  private CourseService courseService = new CourseService();
  private TeacherService teacherService = new TeacherService();
  private TopicService topicService = new TopicService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    Optional<Course> optCourse = courseService.getById(id);
    if (!optCourse.isPresent()) {
      resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
      return;
    }
    List<Topic> topics = topicService.getAll();
    List<Account> teachers = teacherService.getAll();
    req.setAttribute("course", optCourse.get());
    req.setAttribute("topics", topics);
    req.setAttribute("teachers", teachers);
    req.getRequestDispatcher("/edit-course.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    courseService.updateById(id, req.getParameter("name"),
        req.getParameter("startDate"),
        req.getParameter("endDate"),
        req.getParameter("topicId"),
        req.getParameter("teacherId"));
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
