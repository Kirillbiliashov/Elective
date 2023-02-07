package com.example.elective.repository;

import com.example.elective.models.Account;
import com.example.elective.models.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
  @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students s WHERE" +
      " s.student = :student AND c.endDate < CURRENT_DATE")
  List<Course> getCompleted(@Param("student") Account student);
  @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students s " +
      "WHERE c.id <> ALL (SELECT c.id FROM Course c WHERE s.student = :student)" +
      " AND c.startDate > CURRENT_DATE")
  List<Course> getAvailable(@Param("student") Account student);
  @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students s" +
      " WHERE s.student = :student AND CURRENT_DATE BETWEEN c.startDate AND c.endDate")
  List<Course> getOngoing(@Param("student") Account student);
  @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students s " +
      "WHERE s.student = :student AND c.startDate > CURRENT_DATE")
  List<Course> getRegistered(@Param("student") Account student);
  List<Course> findByTeacher(Account teacher, Pageable pageable);
  long countByTeacher(Account teacher);

  @Override
  @EntityGraph(attributePaths = {"students", "topic", "teacher"})
  List<Course> findAll();
}
