package com.example.elective.servlets.teacher;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.services.TeacherService;
import com.example.elective.utils.Constants;
import com.example.elective.utils.RegexUtils;
import com.example.elective.utils.RequestUtils;
import dto.JournalDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    int page = getPageNumber(req);
    setPageAttributes(req, page);
    try {
      req.setAttribute("pagesCount", teacherService.getPagesCount(id));
      Course course = teacherService.getCourseAtPage(id, page).get();
      List<JournalDTO> dtoList = teacherService.getJournalList(course.getId());
      req.setAttribute("journals", dtoList);
      req.setAttribute("course", course);
      req.setAttribute("currDate", Constants.CURRENT_DATE);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("teacher.jsp").forward(req, resp);
  }

  private void setPageAttributes(HttpServletRequest req, int currPage) {
    int nextPage = currPage + 1;
    int prevPage = currPage - 1;
    req.setAttribute("page", currPage);
    req.setAttribute("next", nextPage);
    req.setAttribute("prev", prevPage);
  }

  private int getPageNumber(HttpServletRequest request) {
    String pageParam = request.getParameter("page");
    if (!RegexUtils.isNumeric(pageParam)) return 1;
    return Integer.parseInt(pageParam);
  }

}
