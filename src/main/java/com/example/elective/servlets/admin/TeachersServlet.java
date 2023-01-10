package com.example.elective.servlets.admin;

import static com.example.elective.utils.Constants.*;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet class that extends PaginationHttpServlet and provides page info for mapping "/admin/teachers"
 * @author Kirill Biliashov
 */

@WebServlet("/admin/teachers")
public class TeachersServlet extends PaginationHttpServlet {

  @Override
  void setFields() {
    this.role = TEACHER_ROLE;
    this.attrName = TEACHERS_ATTR;
    this.jspPage = "/admin-teachers.jsp";
  }

}
