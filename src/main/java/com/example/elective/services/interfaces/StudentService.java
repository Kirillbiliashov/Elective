package com.example.elective.services.interfaces;

import com.example.elective.models.Account;

/**
 * Interface describing methods that student service should implement
 * @author Kirill Biliashov
 */

public interface StudentService {
  void changeBlockStatus(int id);
  void save(Account student);
}
