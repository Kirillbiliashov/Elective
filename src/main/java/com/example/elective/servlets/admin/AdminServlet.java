package com.example.elective.servlets.admin;

import com.example.elective.selection.CourseSelection;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseSelectionRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.selection.SortType;
import com.example.elective.services.*;
import com.example.elective.utils.Constants;

import static com.example.elective.utils.Constants.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet class that handles GET request for mapping "/admin"
 * @author Kirill Biliashov
 */

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

  private static final String JSP_PAGE = "/admin.jsp";
  private final RequestMapper<CourseSelection> selectionMapper =
      new CourseSelectionRequestMapper();
  private CourseService courseService;
  private AccountService accService;
  private TopicService topicService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute(COURSE_SERVICE);
    topicService = (TopicService) context.getAttribute(TOPIC_SERVICE);
    accService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      CourseSelection courseSelection = selectionMapper.map(req);
      req.setAttribute(TOPICS_ATTR, topicService.getAll());
      req.setAttribute(COURSES_ATTR, courseService.getBySelection(courseSelection));
      req.setAttribute(TEACHERS_ATTR, accService.getByRole(TEACHER_ROLE));
      req.setAttribute(SORT_TYPES_ATTR, SORT_TYPES);
      req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}