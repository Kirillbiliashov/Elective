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
    String pathInfo = req.getPathInfo();
    int infoLength = pathInfo.length();
    int id = Integer.parseInt(pathInfo.substring(infoLength - 1, infoLength));
    CourseDAO.delete(id);
    resp.sendRedirect("/elective/admin");
  }

}
