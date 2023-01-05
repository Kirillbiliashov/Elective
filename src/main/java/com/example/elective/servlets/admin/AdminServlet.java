package com.example.elective.servlets.admin;

import com.example.elective.selection.CourseSelection;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseSelectionRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.services.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

  private CourseService courseService;
  private TeacherService teacherService;
  private TopicService topicService;

  private RequestMapper<CourseSelection> selectionMapper =
      new CourseSelectionRequestMapper();

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute("courseService");
    topicService = (TopicService) context.getAttribute("topicService");
    teacherService = (TeacherService) context.getAttribute("teacherService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      CourseSelection courseSelection = selectionMapper.map(req);
      req.setAttribute("topics", topicService.getAll());
      req.setAttribute("courses", courseService.getBySelection(courseSelection));
      req.setAttribute("teachers", teacherService.getAll());
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("/admin.jsp").forward(req, resp);
  }

}