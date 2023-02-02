package com.example.elective.dao.interfaces;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Course;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Interface that extends DAO CRUD operations and adds specific ones for course table
 * @author Kirill Biliashov
 */

public interface CourseDAO extends DAO<Course> {

  Optional<Course> findByTeacherId(int teacherId, Pagination pagination);

  List<Course> getCompletedForStudent(int studentId);

  List<Course> getInProgressForStudent(int studentId);

  List<Course> getRegisteredForStudent(int studentId);

  List<Course> getAvailableForStudent(int studentId);

  int getCount(int teacherId);

}
