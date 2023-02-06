package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class containing business logic methods regarding accounts
 * @author Kirill Biliashov
 */

@Service
public class AccountServiceImpl extends AbstractService implements AccountService {

  @Autowired
  private PasswordUtils passwordUtils;

  @Override
  public Optional<Account> findByCredentials(String login, String password) {
    AccountDAO dao = daoFactory.getAccountDAO();
    Optional<Account> optAcc = read(() -> dao.findByLogin(login), dao);
    if (optAcc.isEmpty()) return Optional.empty();
    Account acc = optAcc.get();
    return findByPassword(acc, password);
  }

  private Optional<Account> findByPassword(Account acc, String password) {
    String hashedPassword = acc.getPassword();
    return passwordUtils.match(password, hashedPassword) ?
        Optional.of(acc) : Optional.empty();
  }

  @Override
  public List<Account> getTeachers() {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getByRole(Role.TEACHER), dao);
  }

  @Override
  public List<Account> getPaginatedTeachers(Pagination pagination) {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getByRole(Role.TEACHER, pagination), dao);
  }

  @Override
  public List<Account> getPaginatedStudents(Pagination pagination) {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getByRole(Role.STUDENT, pagination), dao);
  }

  @Override
  public List<String> getLogins() {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(dao::getLogins, dao);
  }

  @Override
  public long getTotalCount(Role role) {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getCountByRole(role), dao);
  }

}
