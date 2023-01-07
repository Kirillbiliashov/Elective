package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AccountService;

import static com.example.elective.utils.Constants.*;

import com.example.elective.utils.PaginationUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/teachers")
public class TeachersServlet extends PaginationHttpServlet {

  @Override
  void setFields() {
    this.role = TEACHER_ROLE;
    this.attrName = TEACHERS_ATTR;
    this.jspPage = "/admin-teachers.jsp";
  }

}
