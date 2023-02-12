package com.example.elective.repository;

import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Topic;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
  @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.students s " +
      "LEFT JOIN FETCH c.topic LEFT JOIN FETCH c.teacher WHERE" +
      " s.student = :student AND c.endDate < CURRENT_DATE")
  List<Course> getCompleted(@Param("student") Account student);
  @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.students s LEFT JOIN FETCH c.topic " +
      "LEFT JOIN FETCH c.teacher " +
      "WHERE (SELECT j FROM Journal j WHERE j.student = :student AND j.course = c) IS NULL" +
      " AND c.startDate > CURRENT_DATE" +
      " AND (:teacher IS NULL OR c.teacher = :teacher)" +
      " AND (:topic IS NULL OR c.topic = :topic)")
  List<Course> getAvailable(Sort sort, @Param("student") Account student,
                            @Param("teacher") Account teacher,
                            @Param("topic") Topic topic);
  @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students s " +
      "LEFT JOIN FETCH c.teacher LEFT JOIN FETCH c.topic" +
      " WHERE s.student = :student AND CURRENT_DATE BETWEEN c.startDate AND c.endDate")
  List<Course> getOngoing(@Param("student") Account student);
  @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students s " +
      "LEFT JOIN FETCH c.teacher LEFT JOIN FETCH c.topic " +
      "WHERE s.student = :student AND c.startDate > CURRENT_DATE")
  List<Course> getRegistered(@Param("student") Account student);

  @Query(value = "SELECT c FROM Course c LEFT JOIN FETCH c.students  " +
      " WHERE c.teacher = :teacher",
      countQuery = "SELECT COUNT(c) FROM Course c")
  Page<Course> findByTeacher(@Param("teacher") Account teacher, Pageable pageable);

  @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.students " +
      "LEFT JOIN FETCH c.topic LEFT JOIN FETCH c.teacher WHERE" +
      " (:teacher IS NULL OR c.teacher = :teacher) " +
      "AND (:topic IS NULL OR c.topic = :topic)")
  List<Course> findAll(Sort sort, @Param("teacher") Account teacher,
                       @Param("topic") Topic topic);

}
