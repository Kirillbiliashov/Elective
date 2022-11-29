package com.example.elective.servlets.teacher;

import com.example.elective.Utils;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Journal;
import com.example.elective.services.JournalService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/teacher/journal/addGrade/*")
public class AddJournalGradeServlet extends HttpServlet {

  private JournalService journalService = new JournalService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int journalId = Utils.getIdFromPathInfo(req.getPathInfo());
    String gradeStr = req.getParameter("grade");
    journalService.updateGradeById(journalId, Integer.parseInt(gradeStr));
    resp.sendRedirect(Utils.TEACHER_SERVLET_NAME);
  }

}
