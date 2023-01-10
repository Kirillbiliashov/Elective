package com.example.elective.dao.interfaces;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Journal;

import java.util.List;
import java.util.Optional;

/**
 * Interface that extends DAO CRUD operations and adds specific ones for journal table
 * @author Kirill Biliashov
 */

public interface JournalDAO extends DAO<Journal> {
  Optional<Journal> findByCourseAndStudent(int courseId, int studentId) throws DAOException;
  List<Journal> getByCourseId(int courseId) throws DAOException;
  int getStudentsCount(int courseId) throws DAOException;

}
