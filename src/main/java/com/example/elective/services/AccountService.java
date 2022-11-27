package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;

import java.util.List;
import java.util.Optional;

public class AccountService {

  private AccountDAO dao = new AccountDAO();

  public Optional<Account> getById(int id) {
    return dao.find(id);
  }

  public Optional<Account> findByCredentials(String login, String password) {
    return dao.findByCredentials(login, password);
  }

  public List<Account> getByRole(String roleName) {
    return dao.getByRole(roleName);
  }

  public void save(Account acc) {
    dao.save(acc);
  }

}
