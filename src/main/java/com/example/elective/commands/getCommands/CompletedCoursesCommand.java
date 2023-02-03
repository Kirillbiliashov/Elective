package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.dto.CompletedCourseDTO;
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

import static com.example.elective.utils.Constants.COMPLETED_COURSES_ATTR;
import static com.example.elective.utils.Constants.COURSE_SERVICE;

/**
 * Class that renders student's completed courses
 * @author Kirill Biliashov
 */

public class CompletedCoursesCommand extends Command {

  private static final String JSP_PAGE = "/student-completed-courses.jsp";
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
    List<Course> courses = service.getCompletedCourses(studentId);
    req.setAttribute(COMPLETED_COURSES_ATTR, getDTOList(courses, studentId));
    forward(JSP_PAGE);
  }

  private List<CompletedCourseDTO> getDTOList(List<Course> courses, int studentId) {
    return courses.stream().map(course -> {
      int grade = course
          .getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get()
          .getGrade();
      return new CompletedCourseDTO(mapper.map(course), grade);
    }).toList();
  }

}
