package com.example.elective.servlets.teacher;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.services.TeacherService;
import com.example.elective.utils.Constants;
import com.example.elective.utils.PaginationUtils;
import com.example.elective.utils.RegexUtils;
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

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

  private TeacherService teacherService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext context = config.getServletContext();
    teacherService = (TeacherService) context.getAttribute("teacherService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int id = RequestUtils.getCurrentUserId(req);
    int page = PaginationUtils.getPageNumber(req);
    PaginationUtils.setPageAttributes(req, page);
    try {
      req.setAttribute("pagesCount", teacherService.getPagesCount(id));
      Optional<Course> optCourse = teacherService.getCourseAtPage(id, page);
      if (optCourse.isPresent()) setAttributes(req, optCourse.get());
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("teacher.jsp").forward(req, resp);
  }

  private void setAttributes(HttpServletRequest req, Course course) throws ServiceException {
    List<JournalDTO> dtoList = teacherService.getJournalList(course.getId());
    req.setAttribute("journals", dtoList);
    req.setAttribute("course", course);
    req.setAttribute("currDate", Constants.CURRENT_DATE);
  }

}
