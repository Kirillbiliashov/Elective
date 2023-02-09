package com.example.elective.repository;

import com.example.elective.models.Account;
import com.example.elective.models.Blocklist;
import org.springframework.cglib.core.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlocklistRepository extends JpaRepository<Blocklist, Integer> {
  Optional<Blocklist> findByStudent(Account student);
}