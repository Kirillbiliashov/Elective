package com.example.elective.controllers;

import com.example.elective.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.controllers.CommandUrlStorage.getCommand;
import static com.example.elective.controllers.CommandUrlStorage.getPostCommand;

@WebServlet({"/login", "/main", "/signup", "/logout", "/admin",
    "/admin/courses/add", "/admin/courses/edit/*",
    "/admin/students/changeBlock/*", "/admin/courses/delete/*",
    "/admin/teachers/register", "/admin/students", "/admin/teachers", "/student",
    "/student/courses/enroll/*", "/student/registered_courses",
    "/student/courses_in_progress", "/student/completed_courses", "/teacher",
    "/teacher/addGrade/*"})
public class FrontController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String url = req.getServletPath();
    Command command = getCommand(url);
    command.init(getServletContext(), req, resp);
    command.process();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String url = req.getServletPath();
    Command command = getPostCommand(url);
    command.init(getServletContext(), req, resp);
    command.process();
  }

}
