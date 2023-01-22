package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.dto.StudentDTO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.dtoMappers.StudentDTOMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Blocklist;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.BlocklistService;

import java.util.ArrayList;
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

  private final BlocklistService blocklistService;

  public AccountServiceImpl(BlocklistService blocklistService) {
    this.blocklistService = blocklistService;
  }

  @Override
  public Optional<Account> findByCredentials(String login, String password)
      throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    Optional<Account> optAcc = read(tm, () -> dao.findByLogin(login));
    if (!optAcc.isPresent()) return Optional.empty();
    Account acc = optAcc.get();
    return findByPassword(acc, password);
  }

  private Optional<Account> findByPassword(Account acc, String password) {
    String hashedPassword = acc.getPassword();
    return passwordsMatch(password, hashedPassword) ?
        Optional.of(acc) : Optional.empty();
  }

  @Override
  public List<Account> getTeachers() throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getByRole(TEACHER_ROLE));
  }

  @Override
  public List<Account> getPaginatedTeachers(Pagination pagination)
      throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getByRole(TEACHER_ROLE, pagination));
  }

  @Override
  public List<StudentDTO> getPaginatedStudents(Pagination pagination)
      throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> {
      List<Account> accountList = dao.getByRole(STUDENT_ROLE, pagination);
      List<StudentDTO> dtoList = new ArrayList<>();
      for (Account account : accountList) dtoList.add(getStudentDTO(tm, account));
      return dtoList;
    });
  }

  private StudentDTO getStudentDTO(TransactionManager tm, Account acc)
      throws MappingException, DAOException {
    Optional<Blocklist> studentBlock =
        blocklistService.getBlockStatus(tm, acc.getId());
    StudentDTOMapper mapper = new StudentDTOMapper(studentBlock.isPresent());
    return mapper.map(acc);
  }

  @Override
  public List<String> getLogins() throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, dao::getLogins);
  }

  @Override
  public int getTotalCount(String roleName) throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getCountByRole(roleName));
  }

  @Override
  public void save(Account acc) throws ServiceException {
    AccountDAO dao = daoFactory.getAccountDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> dao.save(acc));
  }

  @Override
  public Optional<Account> find(TransactionManager tm, int id)
      throws DAOException {
    AccountDAO dao = daoFactory.getAccountDAO();
    tm.initTransaction(dao);
    return dao.find(id);
  }

}
