package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Journal;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.JournalService;

import java.util.Optional;

/**
 * Class containing business logic methods regarding journal
 * @author Kirill Biliashov
 */

public class JournalServiceImpl extends AbstractService implements JournalService {

  @Override
  public void save(Journal journal) throws ServiceException {
    JournalDAO dao = daoFactory.getJournalDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> dao.save(journal));
  }

  @Override
  public void updateGrade(int id, int grade) throws ServiceException {
    JournalDAO dao = daoFactory.getJournalDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> {
      Optional<Journal> optJournal = dao.find(id);
      if (optJournal.isPresent()) {
        Journal journal = optJournal.get();
        journal.getBuilder().setGrade(grade);
        dao.update(journal);
      }
    });
  }

  @Override
  public int getStudentsCount(TransactionManager tm, int courseId)
      throws DAOException {
    JournalDAO dao = daoFactory.getJournalDAO();
    tm.initTransaction(dao);
    return dao.getStudentsCount(courseId);
  }

  @Override
  public Optional<Journal> findByCourseAndStudent(TransactionManager tm,
                                                     int courseId, int studentId)
      throws DAOException {
    JournalDAO dao = daoFactory.getJournalDAO();
    tm.initTransaction(dao);
    return dao.findByCourseAndStudent(courseId, studentId);
  }

}
