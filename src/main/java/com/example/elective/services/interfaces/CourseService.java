package com.example.elective.services.interfaces;

import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.ServiceException;
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

  void save(Course course);

  void delete(int id);

  Optional<Course> findById(int id);

  List<CourseDTO> getBySelection(CourseSelection selection);

  List<CourseDTO> getAvailableBySelection(int studentId, CourseSelection selection);

  List<CompletedCourseDTO> getCompletedCourses(int studentId);

  List<RegisteredCourseDTO> getRegisteredCourses(int studentId);

  List<CourseDTO> getCoursesInProgress(int studentId);

}
