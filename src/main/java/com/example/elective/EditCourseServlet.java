package com.example.elective;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.TopicDAO;
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
import java.util.Optional;

@WebServlet("/courses/edit/*")
public class EditCourseServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    Optional<Course> optCourse = CourseDAO.getById(id);
    if (!optCourse.isPresent()) {
      resp.sendRedirect(Utils.ADMIN_REDIRECT_URL);
      return;
    }
    List<Topic> topics = TopicDAO.getAll();
    List<Account> teachers = AccountDAO.getByRole("Teacher");
    req.setAttribute("course", optCourse.get());
    req.setAttribute("topics", topics);
    req.setAttribute("teachers", teachers);
    req.getRequestDispatcher("/edit-course.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    Optional<Course> optCourse = CourseDAO.getById(id);
    if (optCourse.isPresent()) {
      Course course = optCourse.get();
      setReqParamsForCourse(course, req);
      CourseDAO.update(course);
    }
    resp.sendRedirect(Utils.ADMIN_REDIRECT_URL);
  }

  private void setReqParamsForCourse(Course course, HttpServletRequest req) {
    course.setName(req.getParameter("name"))
        .setDuration(Integer.parseInt(req.getParameter("duration")))
        .setStartDate(Date.valueOf(req.getParameter("startDate")))
        .setTopicId(Integer.parseInt(req.getParameter("topicId")))
        .setTeacherId(Integer.parseInt(req.getParameter("teacherId")));
  }

}
