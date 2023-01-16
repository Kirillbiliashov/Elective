package com.example.elective.services.interfaces;

import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  Optional<Account> findByCredentials(String login, String password) throws ServiceException;

  List<Account> getByRole(String roleName) throws ServiceException;

  List<Account> getPaginated(String role, Pagination pagination) throws ServiceException;

  List<String> getLogins() throws ServiceException;

  int getTotalCount(String roleName) throws ServiceException;

  void save(Account acc) throws ServiceException;

  Optional<Account> find(TransactionManager tm, int id) throws DAOException;
}
