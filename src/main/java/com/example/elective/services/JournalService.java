package com.example.elective.services;

import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Journal;

import java.util.Optional;

public class JournalService extends AbstractService {

  private final JournalDAO dao = daoFactory.getJournalDAO();

  public void save(Journal journal) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.save(journal));
  }

  public void updateGradeById(int id, int grade) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> {
      Optional<Journal> optJournal = dao.find(id);
      if (optJournal.isPresent()) {
        Journal journal = optJournal.get();
        journal.getBuilder().setGrade(grade);
        dao.update(journal);
      }
    });
  }

}
