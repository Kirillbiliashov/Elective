package com.example.elective.services.interfaces;

/**
 * Interface describing methods that journal service should implement
 * @author Kirill Biliashov
 */

public interface JournalService {
  void save(int courseId, int studentId);
  void updateGrade(int id, int grade);
}
