package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.requestMappers.CourseSelectionRequestMapper;
import com.example.elective.mappers.requestMappers.RequestMapper;
import com.example.elective.selection.CourseSelection;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.CourseService;
import com.example.elective.services.interfaces.TopicService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

/**
 * Class that renders admin main page
 * @author Kirill Biliashov
 */

public class AdminCommand extends Command {

  private static final String JSP_PAGE = "/admin.jsp";
  private final RequestMapper<CourseSelection> selectionMapper =
      new CourseSelectionRequestMapper();
  private TopicService topicService;
  private CourseService courseService;
  private AccountService accService;

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
    CourseSelection courseSelection = selectionMapper.map(req);
    try {
      req.setAttribute(TOPICS_ATTR, topicService.getAll());
      req.setAttribute(COURSES_ATTR, courseService.getBySelection(courseSelection));
      req.setAttribute(TEACHERS_ATTR, accService.getTeachers());
      req.setAttribute(SORT_TYPES_ATTR, SORT_TYPES);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
    forward(JSP_PAGE);
  }

}
