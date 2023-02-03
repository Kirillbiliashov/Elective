package com.example.elective.services.interfaces;

import com.example.elective.dto.StudentDTO;
import com.example.elective.models.Account;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that account service should implement
 * @author Kirill Biliashov
 */

public interface AccountService {

  Optional<Account> findByCredentials(String login, String password);

  List<Account> getTeachers();

  List<Account> getPaginatedTeachers(Pagination pagination);
  List<Account> getPaginatedStudents(Pagination pagination);

  List<String> getLogins();

  int getTotalCount(String roleName);

  void save(Account acc);
}
