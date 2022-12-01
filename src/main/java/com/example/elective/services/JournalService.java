package com.example.elective.services;

import com.example.elective.TransactionManager;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Journal;

import java.util.Optional;

public class JournalService {

  private JournalDAO dao = new JournalDAO();
  private TransactionManager transactionManager = new TransactionManager();

  public void save(Journal journal) {
    transactionManager.initTransaction(dao);
    dao.save(journal);
    transactionManager.commitTransaction();
  }

  public void updateGradeById(int id, int grade) {
    transactionManager.initTransaction(dao);
    Optional<Journal> optJournal = dao.find(id);
    if (optJournal.isPresent()) {
      Journal journal = optJournal.get();
      journal.getBuilder().setGrade(grade);
      dao.update(journal);
    }
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
  }

  public int getByCourseIdCount(int courseId) {
    transactionManager.initTransaction(dao);
    int count = dao.getByCourseId(courseId).size();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return count;
  }

}
