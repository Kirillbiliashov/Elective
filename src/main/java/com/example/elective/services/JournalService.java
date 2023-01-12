package com.example.elective.services;

import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Journal;

import java.util.Optional;

/**
 * Class containing business logic methods regarding journal
 * @author Kirill Biliashov
 */

public class JournalService extends AbstractService {

  public void save(Journal journal) throws ServiceException {
    final JournalDAO dao = daoFactory.getJournalDAO();
    TransactionManager tm = new TransactionManager();
    tm.initTransaction(dao);
    write(tm, () -> dao.save(journal));
  }

  public void updateGrade(int id, int grade) throws ServiceException {
    final JournalDAO dao = daoFactory.getJournalDAO();
    TransactionManager tm = new TransactionManager();
    write(tm, () -> {
      Optional<Journal> optJournal = dao.find(id);
      if (optJournal.isPresent()) {
        Journal journal = optJournal.get();
        journal.getBuilder().setGrade(grade);
        dao.update(journal);
      }
    });
  }

  protected int getStudentsCount(TransactionManager tm, int courseId)
      throws DAOException {
    final JournalDAO dao = daoFactory.getJournalDAO();
    tm.initTransaction(dao);
    return dao.getStudentsCount(courseId);
  }

  protected Optional<Journal> findByCourseAndStudent(TransactionManager tm,
                                                     int courseId, int studentId)
      throws DAOException {
    final JournalDAO dao = daoFactory.getJournalDAO();
    tm.initTransaction(dao);
    return dao.findByCourseAndStudent(courseId, studentId);
  }

}
