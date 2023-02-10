package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.repository.AccountRepository;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

/**
 * Class containing business logic methods regarding accounts
 * @author Kirill Biliashov
 */

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

  @Autowired
  private PasswordUtils passwordUtils;
  @Autowired
  private AccountRepository repository;

  @Override
  public Optional<Account> findByCredentials(String login, String password) {
    Optional<Account> optAcc = repository.findByLogin(login);
    return optAcc.isEmpty() ? Optional.empty() :
        findByPassword(optAcc.get(), password);
  }

  private Optional<Account> findByPassword(Account acc, String password) {
    String hashedPassword = acc.getPassword();
    return passwordUtils.match(password, hashedPassword) ?
        Optional.of(acc) : Optional.empty();
  }

  @Override
  public List<Account> getAll(Role role) {
    return repository.getByRole(role);
  }

  @Override
  public Page<Account> getAll(Role role, Integer page, Integer size) {
    boolean isPaginated = page != null && size != null;
    return repository.getByRole(role,
        isPaginated ? PageRequest.of(page, size) : Pageable.unpaged());
  }

}
