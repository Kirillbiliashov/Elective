package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.CourseService;
import com.example.elective.services.interfaces.TopicService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;

public class EditCourseGetCommand extends Command {

  private static final String JSP_PAGE = "/edit-course.jsp";
  private CourseService courseService;
  private AccountService accService;
  private TopicService topicService;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (courseService == null) courseService =
        (CourseService) context.getAttribute(COURSE_SERVICE);
    if (accService == null) accService =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
    if (topicService == null) topicService =
        (TopicService) context.getAttribute(TOPIC_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int id = getIdFromPathInfo();
    try {
      Optional<Course> optCourse = courseService.findById(id);
      if (!optCourse.isPresent()) {
        resp.sendRedirect(ADMIN_URL);
        return;
      }
      req.setAttribute(COURSE_ATTR, optCourse.get());
      req.setAttribute(TOPICS_ATTR, topicService.getAll());
      req.setAttribute(TEACHERS_ATTR, accService.getByRole(TEACHER_ROLE));
      forward(JSP_PAGE);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
