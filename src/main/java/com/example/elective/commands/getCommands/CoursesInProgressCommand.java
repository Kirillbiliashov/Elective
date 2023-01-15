package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.CourseService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.COURSES_IN_PROGRESS_ATTR;
import static com.example.elective.utils.Constants.COURSE_SERVICE;
import static com.example.elective.utils.RequestUtils.getCurrentUserId;

public class CoursesInProgressCommand extends Command {

  private static final String JSP_PAGE = "/student-courses-in-progress.jsp";
  private CourseService service;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (CourseService) context.getAttribute(COURSE_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int studentId = getCurrentUserId(req);
    try {
      req.setAttribute(COURSES_IN_PROGRESS_ATTR,
          service.getCoursesInProgress(studentId));
      forward(JSP_PAGE);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
