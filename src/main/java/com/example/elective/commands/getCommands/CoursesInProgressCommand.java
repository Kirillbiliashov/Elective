package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.models.Course;
import com.example.elective.services.interfaces.CourseService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.elective.utils.Constants.COURSES_IN_PROGRESS_ATTR;
import static com.example.elective.utils.Constants.COURSE_SERVICE;

/**
 * Class that renders student's ongoing courses
 * @author Kirill Biliashov
 */

public class CoursesInProgressCommand extends Command {

  private static final String JSP_PAGE = "/student-courses-in-progress.jsp";
  private CourseService service;
  private final CourseDTOMapper mapper = new CourseDTOMapper();

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (CourseService) context.getAttribute(COURSE_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int studentId = getCurrentUserId();
    List<Course> courses = service.getCoursesInProgress(studentId);
    req.setAttribute(COURSES_IN_PROGRESS_ATTR, courses
        .stream()
        .map(mapper::map)
        .toList());
    forward(JSP_PAGE);
  }

}
