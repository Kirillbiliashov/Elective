package com.example.elective.servlets.admin;

import com.example.elective.Utils;
import com.example.elective.dao.CourseDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/courses/delete/*")
public class DeleteCourseServlet extends HttpServlet {

  private CourseService courseService = new CourseService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    try {
      courseService.delete(id);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    resp.sendRedirect(Utils.ADMIN_SERVLET_NAME);
  }

}
