package com.example.elective.servlets.teacher;

import com.example.elective.Utils;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.services.CourseService;
import com.example.elective.services.JournalService;
import com.example.elective.services.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

  private TeacherService teacherService = new TeacherService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int id = Utils.getCurrentUserId(req);
    int page = getPageNumber(req);
    setPageAttributes(req, page);
    try {
      req.setAttribute("pagesCount", teacherService.getPagesCount(id));
      Course course = teacherService.getCourseAtPage(id, page).get();
      Map<Journal, Account> journal = teacherService.getJournalForCourse(course.getId());
      req.setAttribute("journal", journal);
      req.setAttribute("course", course);
      req.setAttribute("currDate", Utils.CURRENT_DATE);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    req.getRequestDispatcher("teacher.jsp").forward(req, resp);
  }

  private void setPageAttributes(HttpServletRequest req, int currPage) {
    int nextPage = currPage + 1;
    int prevPage = currPage - 1;
    req.setAttribute("next", nextPage);
    req.setAttribute("prev", prevPage);
  }

  private int getPageNumber(HttpServletRequest request) {
    String pageParam = request.getParameter("page");
    if (!Utils.isNumeric(pageParam)) return 1;
    return Integer.parseInt(pageParam);
  }

}
