package com.example.elective.services.interfaces;

import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that account service should implement
 * @author Kirill Biliashov
 */

public interface AccountService {
  Optional<Account> findByCredentials(String login, String password);
  List<Account> getAll(Role role);
  List<Account> getPaginated(Role role, Pagination pagination);
  List<String> getLogins();
  long getTotalCount(Role role);
}
