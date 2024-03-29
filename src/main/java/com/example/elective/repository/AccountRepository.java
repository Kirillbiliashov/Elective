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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  @Query("SELECT a FROM Account a LEFT JOIN FETCH a.block WHERE a.role = :role")
  List<Account> getByRole(@Param("role") Role role);

  @Query(value = "SELECT a FROM Account a LEFT JOIN FETCH a.block WHERE a.role = :role",
      countQuery = "SELECT COUNT(a) FROM Account a WHERE role = :role")
  Page<Account> getByRole(@Param("role") Role role, Pageable pageable);
  @Query("SELECT a FROM Account a LEFT JOIN FETCH a.block WHERE a.username = ?1 OR a.email = ?1")
  Account getByLogin(String login);
  Account getByUsername(String username);
  Optional<Account> findByUsername(String username);
  Optional<Account> findByEmail(String email);

}
