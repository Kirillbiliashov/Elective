package com.example.elective;

import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/main")
public class MainPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    Account account = (Account) session.getAttribute("account");
    int roleId = account.getRoleId();
    Optional<Role> optRole = RoleDAO.findById(roleId);
    String jspPage = "error.jsp";
    if (optRole.isPresent()) {
      Role role = optRole.get();
      String roleName = role.getName();
      if (roleName.equals("Student")) jspPage = "student.jsp";
      else if (roleName.equals("Teacher")) jspPage = "teacher.jsp";
      else if (roleName.equals("Admin")) jspPage = "admin.jsp";
    }
    req.getRequestDispatcher(jspPage).forward(req, resp);
  }

}
