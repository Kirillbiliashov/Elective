package com.example.elective.servlets;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("courses", getCourseAccountMap(CourseDAO.getAll()));
    req.setAttribute("students", AccountDAO.getByRole("Student"));
    req.setAttribute("teachers", AccountDAO.getByRole("Teacher"));
    req.getRequestDispatcher("admin.jsp").forward(req, resp);
  }

  private Map<Course, Account> getCourseAccountMap(List<Course> courses) {
    Map<Course, Account> map = new LinkedHashMap<>();
    courses.forEach(course -> map.put(course,
        AccountDAO.getById(course.getTeacherId()).orElse(null)));
    return map;
  }

}
