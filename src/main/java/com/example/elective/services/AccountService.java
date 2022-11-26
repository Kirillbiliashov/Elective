package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountService {

  public Optional<Account> findByCredentials(String login, String password) {
    return AccountDAO.findByCredentials(login, password);
  }

  public List<Account> getByRole(String roleName) {
    return AccountDAO.getByRole(roleName);
  }

  public void save(Account acc) {
    AccountDAO.save(acc);
  }

  public void changeBlockStatus(int id) {
    Optional<Account> optAcc = AccountDAO.getById(id);
    if (optAcc.isPresent()) {
      Account acc = optAcc.get();
      acc.setBlocked(!acc.isBlocked());
      AccountDAO.update(acc);
    }
  }

}
