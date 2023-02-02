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
    Session session = SQLDAOFactory.getSession();
    AccountDAO dao = daoFactory.getAccountDAO();
    dao.setSession(session);
    session.beginTransaction();
    Optional<Account> optAcc = dao.findByLogin(login);
    session.getTransaction().commit();
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
    Session session = SQLDAOFactory.getSession();
    AccountDAO dao = daoFactory.getAccountDAO();
    dao.setSession(session);
    session.beginTransaction();
    try {
      return dao.getByRole(TEACHER_ROLE);
    } finally {
      session.getTransaction().commit();
    }
  }

  @Override
  public List<Account> getPaginatedTeachers(Pagination pagination) {
    Session session = SQLDAOFactory.getSession();
    AccountDAO dao = daoFactory.getAccountDAO();
    dao.setSession(session);
    session.beginTransaction();
    try {
      return dao.getByRole(TEACHER_ROLE, pagination);
    } finally {
      session.getTransaction().commit();
    }
  }

  @Override
  public List<StudentDTO> getPaginatedStudents(Pagination pagination) {
    Session session = SQLDAOFactory.getSession();
    AccountDAO dao = daoFactory.getAccountDAO();
    dao.setSession(session);
    session.beginTransaction();
    List<Account> accountList = dao.getByRole(STUDENT_ROLE, pagination);
    session.getTransaction().commit();
    StudentDTOMapper mapper = new StudentDTOMapper();
    return accountList.stream().map(mapper::map).toList();
  }

  @Override
  public List<String> getLogins() {
    AccountDAO dao = daoFactory.getAccountDAO();
    Session session = SQLDAOFactory.getSession();
    dao.setSession(session);
    session.beginTransaction();
    try {
      return dao.getLogins();
    } finally {
      session.getTransaction().commit();
    }
  }

  @Override
  public int getTotalCount(String roleName) {
    AccountDAO dao = daoFactory.getAccountDAO();
    Session session = SQLDAOFactory.getSession();
    session.beginTransaction();
    try {
      return dao.getCountByRole(roleName);
    } finally {
      session.getTransaction().commit();
    }
  }

  @Override
  public void save(Account acc) {
    Session session = SQLDAOFactory.getSession();
    AccountDAO dao = daoFactory.getAccountDAO();
    dao.setSession(session);
    session.beginTransaction();
    dao.save(acc);
    session.getTransaction().commit();
  }

}
