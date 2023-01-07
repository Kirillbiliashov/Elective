package com.example.elective.servlets.teacher;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.selection.CoursePagination;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AccountService;
import com.example.elective.services.TeacherService;
import com.example.elective.utils.PaginationUtils;
import com.example.elective.utils.RequestUtils;
import com.example.elective.dto.JournalDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.PaginationUtils.setPageAttributes;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

  private static final String JSP_PAGE = "teacher.jsp";
  private TeacherService teacherService;
  private AccountService accountService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    teacherService = (TeacherService) context.getAttribute(TEACHER_SERVICE);
    accountService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int id = RequestUtils.getCurrentUserId(req);
    int page = PaginationUtils.getPageNumber(req);
    setPageAttributes(req, page);
    try {
      int total = accountService.getTotalCount(TEACHER_ROLE);
      Pagination pagination = new CoursePagination(page, total);
      req.setAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
      Optional<Course> optCourse = teacherService.findCourseAtPage(id, pagination);
      if (optCourse.isPresent()) setAttributes(req, optCourse.get());
      req.getRequestDispatcher(JSP_PAGE).forward(req, resp);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void setAttributes(HttpServletRequest req, Course course)
      throws ServiceException {
    List<JournalDTO> dtoList = teacherService.getJournalList(course.getId());
    req.setAttribute(JOURNALS_ATTR, dtoList);
    req.setAttribute(COURSE_ATTR, course);
    req.setAttribute(CURR_DATE_ATTR, CURRENT_DATE);
  }

}
