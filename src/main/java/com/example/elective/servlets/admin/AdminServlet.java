package com.example.elective.servlets.admin;

import com.example.elective.CourseSelection;
import com.example.elective.Utils;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseSelectionRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.models.Course;
import com.example.elective.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

  private CourseService courseService = new CourseService();
  private TeacherService teacherService = new TeacherService();
  private TopicService topicService = new TopicService();
  private RequestMapper<CourseSelection> selectionMapper = new CourseSelectionRequestMapper();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      CourseSelection courseSelection = selectionMapper.map(req);
      System.out.println("course selection: " + courseSelection);
      List<Course> courses = courseSelection == null ? courseService.getAll() :
          courseService.getBySelection(courseSelection);
      req.setAttribute("topics", topicService.getAll());
      req.setAttribute("courses", courseService.getCourseTeacher(courses));
      req.setAttribute("teachers", teacherService.getAll());
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/admin.jsp").forward(req, resp);
  }

}
