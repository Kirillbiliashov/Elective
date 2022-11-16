package com.example.elective;

import com.example.elective.dao.CourseDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/courses/delete/*")
public class DeleteCourseServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int id = Utils.getIdFromPathInfo(req.getPathInfo());
    CourseDAO.delete(id);
    resp.sendRedirect(Utils.ADMIN_REDIRECT_URL);
  }

}
