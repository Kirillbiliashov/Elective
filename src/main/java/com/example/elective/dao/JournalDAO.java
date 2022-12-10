package com.example.elective.dao;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Journal;

import java.util.List;
import java.util.Optional;

public interface JournalDAO extends DAO<Journal> {
  Optional<Journal> findByCourseAndStudent(int courseId, int studentId) throws DAOException;
  List<Journal> getByCourseId(int courseId) throws DAOException;

}
