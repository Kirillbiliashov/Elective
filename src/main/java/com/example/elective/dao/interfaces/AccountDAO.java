package com.example.elective.dao.interfaces;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDAO extends DAO<Account> {
   List<Account> findByRole(String roleName) throws DAOException;
   List<Account> findByRole(String roleName, int page) throws DAOException;
   Optional<Account> findByLogin(String login) throws DAOException;
   int getCountByRole(String roleName) throws DAOException;
}
