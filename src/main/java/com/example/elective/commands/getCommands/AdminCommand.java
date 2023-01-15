package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseSelectionRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.selection.CourseSelection;
import com.example.elective.services.AccountService;
import com.example.elective.services.CourseService;
import com.example.elective.services.TopicService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

public class AdminCommand extends Command {

  private static final String JSP_PAGE = "/admin.jsp";
  private TopicService topicService;
  private CourseService courseService;
  private AccountService accService;
  private final RequestMapper<CourseSelection> selectionMapper =
      new CourseSelectionRequestMapper();

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (topicService == null) topicService =
        (TopicService) context.getAttribute(TOPIC_SERVICE);
    if (courseService == null) courseService =
        (CourseService) context.getAttribute(COURSE_SERVICE);
    if (accService == null) accService =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    try {
      CourseSelection courseSelection = selectionMapper.map(req);
      req.setAttribute(TOPICS_ATTR, topicService.getAll());
      req.setAttribute(COURSES_ATTR, courseService.getBySelection(courseSelection));
      req.setAttribute(TEACHERS_ATTR, accService.getByRole(TEACHER_ROLE));
      req.setAttribute(SORT_TYPES_ATTR, SORT_TYPES);
      forward(JSP_PAGE);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
