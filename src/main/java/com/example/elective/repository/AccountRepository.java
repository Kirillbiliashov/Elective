package com.example.elective.repository;

import com.example.elective.models.Account;
import com.example.elective.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  List<Account> getByRole(Role role);
  Page<Account> getByRole(Role role, Pageable pageable);
  long countByRole(Role role);
  @Query("SELECT a FROM Account a WHERE a.username = ?1 OR a.email = ?1")
  Optional<Account> findByLogin(String login);
  @Query("SELECT CONCAT(a.username, ',', a.email) FROM Account a ")
  List<String> getLogins();
  Account getByUsername(String username);

}
