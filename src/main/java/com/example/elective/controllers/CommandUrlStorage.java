package com.example.elective.controllers;

import com.example.elective.commands.Command;
import com.example.elective.commands.getCommands.*;
import com.example.elective.commands.postCommands.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandUrlStorage {

  private static final Map<String, Command> getMap = new HashMap<>();
  private static final Map<String, Command> postMap = new HashMap<>();

  static {
    getMap.put("/login", new LoginGetCommand());
    getMap.put("/main", new MainCommand());
    getMap.put("/signup", new SignupGetCommand());
    getMap.put("/logout", new LogoutCommand());
    getMap.put("/admin/courses/add", new AddCourseGetCommand());
    getMap.put("/admin", new AdminCommand());
    getMap.put("/admin/courses/edit", new EditCourseGetCommand());
    getMap.put("/admin/teachers/register", new TeacherRegistrationGetCommand());
    getMap.put("/admin/students", new StudentsCommand());
    getMap.put("/admin/teachers", new TeachersCommand());
    getMap.put("/student", new StudentCommand());
    getMap.put("/student/registered_courses", new RegisteredCoursesCommand());
    getMap.put("/student/courses_in_progress", new CoursesInProgressCommand());
    getMap.put("/student/completed_courses", new CompletedCoursesCommand());
    getMap.put("/teacher", new TeacherCommand());
    postMap.put("/login", new LoginPostCommand());
    postMap.put("/signup", new SignupPostCommand());
    postMap.put("/admin/courses/add", new AddCoursePostCommand());
    postMap.put("/admin/students/changeBlock", new ChangeBlockCommand());
    postMap.put("/admin/courses/delete", new DeleteCourseCommand());
    postMap.put("/admin/courses/edit", new EditCoursePostCommand());
    postMap.put("/admin/teachers/register", new TeacherRegistrationPostCommand());
    postMap.put("/student/courses/enroll", new CourseEnrollCommand());
    postMap.put("/teacher/addGrade", new AddGradeCommand());
  }

  public static Command getCommand(String url) {
    return getMap.get(url);
  }

  public static Command getPostCommand(String url) {
    return postMap.get(url);
  }

}
