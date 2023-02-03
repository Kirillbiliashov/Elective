package com.example.elective.services.interfaces;

import com.example.elective.models.Course;
import com.example.elective.selection.CourseSelection;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that course service should implement
 * @author Kirill Biliashov
 */

public interface CourseService {

  void update(Course course);

  void save(Course course, int topicId, int teacherId);

  void delete(int id);

  Optional<Course> findById(int id);

  List<Course> getAll();

  List<Course> getAvailable(int studentId);

  List<Course> getCompletedCourses(int studentId);

  List<Course> getRegisteredCourses(int studentId);

  List<Course> getCoursesInProgress(int studentId);

}
