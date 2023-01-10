package com.example.elective.servlets.student;

import com.example.elective.selection.CourseSelection;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseSelectionRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.services.CourseService;
import com.example.elective.services.TeacherService;
import com.example.elective.services.TopicService;
import com.example.elective.utils.Constants;
import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.RequestUtils.getCurrentUserId;

/**
 * Servlet class that handles GET request url for mapping "/student"
 * @author Kirill Biliashov
 */

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

  private static final String JSP_PAGE = "student.jsp";
  private final RequestMapper<CourseSelection> selectionMapper =
      new CourseSelectionRequestMapper();
  private CourseService courseService;
  private TeacherService teacherService;
  private TopicService topicService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute(COURSE_SERVICE);
    topicService = (TopicService) context.getAttribute(TOPIC_SERVICE);
    teacherService = (TeacherService) context.getAttribute(TEACHER_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int studentId = getCurrentUserId(req);
    try {
      CourseSelection courseSelection = selectionMapper.map(req);
      req.setAttribute(TOPICS_ATTR, topicService.getAll());
      req.setAttribute(TEACHERS_ATTR, teacherService.getAll());
      req.setAttribute(AVAILABLE_COURSES_ATTR,
          courseService.getAvailableBySelection(studentId, courseSelection));
      req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}