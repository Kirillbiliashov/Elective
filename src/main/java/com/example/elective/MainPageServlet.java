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
      throws IOException {
    HttpSession session = req.getSession();
    Account account = (Account) session.getAttribute("account");
    int roleId = account.getRoleId();
    Optional<Role> optRole = RoleDAO.findById(roleId);
    String servletUrl = "error.jsp";
    if (optRole.isPresent()) {
      Role role = optRole.get();
      String roleName = role.getName();
      if (roleName.equals("Student")) servletUrl = "student";
      else if (roleName.equals("Teacher")) servletUrl = "teacher";
      else if (roleName.equals("Admin")) servletUrl = "admin";
    }
    resp.sendRedirect(servletUrl);
  }

}
