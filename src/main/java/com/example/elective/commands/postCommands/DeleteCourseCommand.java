package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.interfaces.CourseService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ADMIN_URL;
import static com.example.elective.utils.Constants.COURSE_SERVICE;

/**
 * Class with method that calls corresponding service method when client deletes a course
 * @author Kirill Biliashov
 */

public class DeleteCourseCommand extends Command {

  private CourseService service;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (CourseService) context.getAttribute(COURSE_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int id = getIdFromPathInfo();
    service.delete(id);
    resp.sendRedirect(ADMIN_URL);
  }

}