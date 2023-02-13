package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.repository.AccountRepository;
import com.example.elective.services.interfaces.AccountService;
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
  private AccountRepository repository;

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
