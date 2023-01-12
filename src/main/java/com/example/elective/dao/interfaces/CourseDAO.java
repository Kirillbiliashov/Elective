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

  Optional<Course> findByTeacherId(int teacherId, Pagination pagination) throws DAOException;

  List<Course> getCompletedForStudent(int studentId) throws DAOException;

  List<Course> getInProgressForStudent(int studentId) throws DAOException;

  List<Course> getRegisteredForStudent(int studentId) throws DAOException;

  List<Course> getAvailableForStudent(int studentId) throws DAOException;

  int getCount(int teacherId) throws DAOException;

}
