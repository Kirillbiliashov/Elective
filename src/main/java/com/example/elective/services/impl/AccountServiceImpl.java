package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.repository.AccountRepository;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.utils.Pagination;
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

  private final AccountRepository repository;

  @Autowired
  public AccountServiceImpl(AccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Account> getAll(Role role) {
    return repository.getByRole(role);
  }

  @Override
  public Page<Account> getAll(Role role, Pagination pagination) {
    return repository.getByRole(role, pagination.isPaginated() ?
        PageRequest.of(pagination.getPage(), pagination.getSize()) :
        Pageable.unpaged());
  }

  @Override
  public Optional<Account> findByUsername(String username) {
    return repository.findByUsername(username);
  }

  @Override
  public Optional<Account> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public Account get(int id) {
    return repository.findById(id).orElse(null);
  }

}
