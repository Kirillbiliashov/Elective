package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JournalService {

  public void save(Journal journal) {
    JournalDAO.save(journal);
  }

  public void updateGradeById(int id, int grade) {
    Optional<Journal> optJournal = JournalDAO.getById(id);
    if (optJournal.isPresent()) {
      Journal journal = optJournal.get();
      journal.setGrade(grade);
      JournalDAO.update(journal);
    }
  }

  public Map<Journal, Account> getJournalStudent(int courseId) {
    Map<Journal, Account> map = new LinkedHashMap<>();
    List<Journal> journalList = JournalDAO.getByCourseId(courseId);
    for (final Journal journal: journalList) {
      Account student = AccountDAO.getById(journal.getStudentId()).orElse(null);
      map.put(journal, student);
    }
    return map;
  }
}
