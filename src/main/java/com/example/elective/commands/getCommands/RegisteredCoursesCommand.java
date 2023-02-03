package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.services.interfaces.CourseService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static com.example.elective.utils.Constants.COURSE_SERVICE;
import static com.example.elective.utils.Constants.REGISTERED_COURSES_ATTR;

/**
 * Class that renders student's registered courses
 * @author Kirill Biliashov
 */

public class RegisteredCoursesCommand extends Command {

  private static final String JSP_PAGE = "/student-registered-courses.jsp";
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
    List<Course> courses = service.getRegisteredCourses(studentId);
    req.setAttribute(REGISTERED_COURSES_ATTR, getDTOList(courses, studentId));
    forward(JSP_PAGE);
  }

  private List<RegisteredCourseDTO> getDTOList(List<Course> courses, int studentId) {
    return courses.stream().map(course -> {
      Journal studentCourse = course
          .getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get();
      Date registrationDate = studentCourse.getEnrollmentDate();
      return new RegisteredCourseDTO(mapper.map(course), registrationDate);
    }).toList();
  }

}
