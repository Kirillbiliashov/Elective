package com.example.elective.repository;

import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
  List<Journal> getByCourse(Course course);
}
