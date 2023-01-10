package com.example.elective.servlets.admin;

import javax.servlet.annotation.WebServlet;

import static com.example.elective.utils.Constants.*;

/**
 * Servlet class that extends PaginationHttpServlet and provides page info for mapping "/admin/students"
 * @author Kirill Biliashov
 */

@WebServlet("/admin/students")
public class StudentsServlet extends PaginationHttpServlet {

  @Override
  void setFields() {
    this.role = STUDENT_ROLE;
    this.attrName = STUDENTS_ATTR;
    this.jspPage = "/admin-students.jsp";
  }

}
