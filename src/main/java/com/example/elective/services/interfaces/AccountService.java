package com.example.elective.services.interfaces;

import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.utils.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that account service should implement
 * @author Kirill Biliashov
 */

public interface AccountService {
  List<Account> getAll(Role role);
  Page<Account> getAll(Role role, Pagination pagination);
  Optional<Account> findByUsername(String username);
  Optional<Account> findByEmail(String email);
  Account get(int id);
}