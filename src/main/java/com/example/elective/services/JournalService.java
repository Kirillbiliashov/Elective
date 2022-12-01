package com.example.elective.services;

import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Journal;

import java.util.Optional;

public class JournalService extends AbstractService {

  private JournalDAO dao = new JournalDAO();

  public void save(Journal journal) {
    transactionManager.initTransaction(dao);
    performWriteOperation(() -> dao.save(journal));
  }

  public void updateGradeById(int id, int grade) {
    transactionManager.initTransaction(dao);
    performWriteOperation(() -> {
      Optional<Journal> optJournal = dao.find(id);
      if (optJournal.isPresent()) {
        Journal journal = optJournal.get();
        journal.getBuilder().setGrade(grade);
        dao.update(journal);
      }
    });
  }

  public int getByCourseIdCount(int courseId) {
    transactionManager.initTransaction(dao);
    return performReadOperation(() -> dao.getByCourseId(courseId).size());
  }

}
