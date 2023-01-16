package com.example.elective.controllers;

import com.example.elective.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.commands.CommandStorage.getCommand;
import static com.example.elective.commands.CommandStorage.getPostCommand;

/**
 * Class extending HttpServlet that handles all app requests and processes corresponding command
 * @author Kirill Biliashov
 */

@WebServlet({"/login", "/main", "/signup", "/logout", "/admin",
    "/admin/courses/add", "/admin/courses/edit/*",
    "/admin/students/changeBlock/*", "/admin/courses/delete/*",
    "/admin/teachers/register", "/admin/students", "/admin/teachers", "/student",
    "/student/courses/enroll/*", "/student/registered_courses",
    "/student/courses_in_progress", "/student/completed_courses", "/teacher",
    "/teacher/addGrade/*"})
public class FrontController extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String url = req.getServletPath();
    Command command = req.getMethod().equals("GET") ? getCommand(url) :
        getPostCommand(url);
    command.init(getServletContext(), req, resp);
    command.process();
  }

}
