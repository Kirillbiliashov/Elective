package com.example.elective.servlets;

import com.example.elective.Utils;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Journal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/journal/addGrade/*")
public class AddJournalGradeServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    int journalId = Utils.getIdFromPathInfo(req.getPathInfo());
    Optional<Journal> optJournal = JournalDAO.getById(journalId);
    if (optJournal.isPresent()) {
      Journal journal = optJournal.get();
      journal.setGrade(Integer.parseInt(req.getParameter("grade")));
      JournalDAO.update(journal);
    }
    resp.sendRedirect(Utils.TEACHER_REDIRECT_URL);
  }

}
