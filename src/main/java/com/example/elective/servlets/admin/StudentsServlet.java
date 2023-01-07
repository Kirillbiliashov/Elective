package com.example.elective.servlets.admin;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AccountService;
import com.example.elective.utils.PaginationUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.*;
import static com.example.elective.utils.PaginationUtils.getItemsPerPage;
import static com.example.elective.utils.PaginationUtils.getPageNumber;

@WebServlet("/admin/students")
public class StudentsServlet extends PaginationHttpServlet {

  @Override
  void setFields() {
    this.role = STUDENT_ROLE;
    this.attrName = STUDENTS_ATTR;
    this.jspPage = "/admin-students.jsp";
  }

}
