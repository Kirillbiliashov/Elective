package com.example.elective.services.interfaces;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Journal;

/**
 * Interface describing methods that journal service should implement
 * @author Kirill Biliashov
 */

public interface JournalService {

  void save(int courseId, int studentId);
  void updateGrade(int id, int grade);
}
