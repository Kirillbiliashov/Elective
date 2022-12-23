package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.CourseService;
import com.example.elective.utils.Constants;
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
    courseService = (CourseService) context.getAttribute("courseService");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int id = RequestUtils.getIdFromPathInfo(req.getPathInfo());
    try {
      courseService.delete(id);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(RequestUtils.getRedirectUrl(req, Constants.ADMIN_SERVLET_NAME));
  }

}
