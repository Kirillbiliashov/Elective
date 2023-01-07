package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.CourseService;
import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.RequestUtils.getIdFromPathInfo;

import com.example.elective.utils.RequestUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/courses/delete/*")
public class DeleteCourseServlet extends HttpServlet {

  private CourseService courseService;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    courseService = (CourseService) context.getAttribute(COURSE_SERVICE);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int id = getIdFromPathInfo(req.getPathInfo());
    try {
      courseService.delete(id);
      resp.sendRedirect(ADMIN_SERVLET_NAME);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
