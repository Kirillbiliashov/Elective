package com.example.elective;

import com.example.elective.dao.StudentDAO;
import com.example.elective.dao.TeacherDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/main")
public class MainPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    final int accId = (int) session.getAttribute("accountId");
    String userType = (String) session.getAttribute("userType");
    String jspPage = "error.jsp";
    if (accId == 1) {
      jspPage = "admin.jsp";
    } else if (userType.equals("Student") &&
        StudentDAO.findByAccountId(accId).isPresent()) {
      jspPage = "student.jsp";
    } else if (userType.equals("Teacher") &&
        TeacherDAO.findByAccountId(accId).isPresent()) {
      jspPage = "teacher.jsp";
    }
    req.getRequestDispatcher(jspPage).forward(req, resp);
  }

}
