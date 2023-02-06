package com.example.elective.dao.interfaces;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Interface that extends DAO CRUD operations and adds specific ones for account table
 * @author Kirill Biliashov
 */

public interface AccountDAO extends DAO<Account> {

  List<Account> getByRole(Role role);

  List<Account> getByRole(Role role, Pagination pagination);

  List<String> getLogins();

  Optional<Account> findByLogin(String login);

  long getCountByRole(Role role);

}
