package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;

import java.util.Optional;

public class JournalService {

  private JournalDAO dao = new JournalDAO();

  public void save(Journal journal) {
    dao.save(journal);
  }

  public void updateGradeById(int id, int grade) {
    Optional<Journal> optJournal = dao.getById(id);
    if (optJournal.isPresent()) {
      Journal journal = optJournal.get();
      journal.setGrade(grade);
      dao.update(journal);
    }
  }

  public int getByCourseIdCount(int courseId) {
    return dao.getByCourseId(courseId).size();
  }

}
