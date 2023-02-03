package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.dto.JournalDTO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.selection.CoursePagination;
import com.example.elective.selection.Pagination;
import com.example.elective.services.interfaces.TeacherService;
import com.example.elective.utils.PaginationUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.PaginationUtils.setPageAttributes;

/**
 * Class that renders teacher's main page
 * @author Kirill Biliashov
 */

public class TeacherCommand extends Command {

  private TeacherService service;
  private static final String JSP_PAGE = "teacher.jsp";

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (TeacherService) context.getAttribute(TEACHER_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int id = getCurrentUserId();
    int page = PaginationUtils.getPageNumber(req);
    setPageAttributes(req, page);
    int total = service.getCoursesCount(id);
    Pagination pagination = new CoursePagination(page, total);
    setPageAttributes(req, pagination.getPage());
    req.setAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
    Optional<Course> optCourse = service.findCourse(id, pagination);
    optCourse.ifPresent(this::setAttributes);
    forward(JSP_PAGE);
  }

    private void setAttributes(Course course) {
    List<JournalDTO> dtoList = service.getJournalList(course.getId());
    req.setAttribute(JOURNALS_ATTR, dtoList);
    req.setAttribute(COURSE_ATTR, course);
    req.setAttribute(CURR_DATE_ATTR, CURRENT_DATE);
  }

}