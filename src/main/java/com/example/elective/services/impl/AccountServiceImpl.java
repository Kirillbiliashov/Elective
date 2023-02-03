package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.dto.StudentDTO;
import com.example.elective.mappers.dtoMappers.StudentDTOMapper;
import com.example.elective.models.Account;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.AccountService;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static com.example.elective.utils.Constants.STUDENT_ROLE;
import static com.example.elective.utils.Constants.TEACHER_ROLE;
import static com.example.elective.utils.PasswordUtils.passwordsMatch;

/**
 * Class containing business logic methods regarding accounts
 * @author Kirill Biliashov
 */

public class AccountServiceImpl extends AbstractService implements AccountService {

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
    return passwordsMatch(password, hashedPassword) ?
        Optional.of(acc) : Optional.empty();
  }

  @Override
  public List<Account> getTeachers() {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getByRole(TEACHER_ROLE), dao);
  }

  @Override
  public List<Account> getPaginatedTeachers(Pagination pagination) {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getByRole(TEACHER_ROLE, pagination), dao);
  }

  @Override
  public List<Account> getPaginatedStudents(Pagination pagination) {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getByRole(STUDENT_ROLE, pagination), dao);
  }

  @Override
  public List<String> getLogins() {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(dao::getLogins, dao);
  }

  @Override
  public int getTotalCount(String roleName) {
    AccountDAO dao = daoFactory.getAccountDAO();
    return read(() -> dao.getCountByRole(roleName), dao);
  }

  @Override
  public void save(Account acc) {
    AccountDAO dao = daoFactory.getAccountDAO();
    write(() -> dao.save(acc), dao);
  }

}
