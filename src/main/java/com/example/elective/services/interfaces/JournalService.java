package com.example.elective.services.interfaces;

import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Journal;

import java.util.Optional;

/**
 * Interface describing methods that journal service should implement
 * @author Kirill Biliashov
 */

public interface JournalService {

  void save(Journal journal) throws ServiceException;
  void updateGrade(int id, int grade) throws ServiceException;
  Optional<Journal> findByCourseAndStudent(TransactionManager tm, int courseId,
                                           int studentId) throws DAOException;
  int getStudentsCount(TransactionManager tm, int courseId) throws DAOException;
}
