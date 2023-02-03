package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.TopicService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;

/**
 * Command class that renders JSP page for adding course
 * @author Kirill Biliashov
 */

public class AddCourseGetCommand extends Command {

  private static final String JSP_PAGE = "/add-course.jsp";
  private TopicService topicService;
  private AccountService accService;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (topicService == null) topicService =
        (TopicService) context.getAttribute(TOPIC_SERVICE);
    if (accService == null) accService =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    req.setAttribute(TOPICS_ATTR, topicService.getAll());
    req.setAttribute(TEACHERS_ATTR, accService.getTeachers());
    forward(JSP_PAGE);
  }

}