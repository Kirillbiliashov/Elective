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
  List<Journal> getByCourseId(int courseId);

}
