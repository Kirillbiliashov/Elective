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

  void update(Course course) throws ServiceException;

  void save(Course course) throws ServiceException;

  void delete(int id) throws ServiceException;

  Optional<Course> findById(int id) throws ServiceException;

  List<CourseDTO> getBySelection(CourseSelection selection) throws ServiceException;

  List<CourseDTO> getAvailableBySelection(int studentId, CourseSelection selection)
      throws ServiceException;

  List<CompletedCourseDTO> getCompletedCourses(int studentId) throws ServiceException;

  List<RegisteredCourseDTO> getRegisteredCourses(int studentId)
      throws ServiceException;

  List<CourseDTO> getCoursesInProgress(int studentId) throws ServiceException;

}
