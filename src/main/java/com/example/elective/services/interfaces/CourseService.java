package com.example.elective.services.interfaces;

import com.example.elective.models.Course;
import com.example.elective.utils.CourseSelection;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that course service should implement
 * @author Kirill Biliashov
 */

public interface CourseService {
  void persist(Course course, int teacherId, int topicId);
  void delete(int id);
  Optional<Course> findById(int id);
  List<Course> getAll(CourseSelection selection);
  List<Course> getAvailable(int studentId, CourseSelection selection);
  List<Course> getCompletedCourses(int studentId);
  List<Course> getRegisteredCourses(int studentId);
  List<Course> getOngoingCourses(int studentId);
}
