package com.example.elective.dao;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDAO extends DAO<Course> {
  List<Course> getAllOrderedByStudentCount(boolean isAsc) throws DAOException;
  List<Course> getOrderedBy(String orderBy) throws DAOException;
  List<Course> getByTeacherId(int teacherId) throws DAOException;
  Optional<Course> getByTeacherIdAtPosition(int teacherId, int position) throws DAOException;
  List<Course> findCompletedForStudent(int studentId) throws DAOException;
  List<Course> findInProgressForStudent(int studentId) throws DAOException;
  List<Course> findRegisteredForStudent(int studentId) throws DAOException;
  List<Course> findAvailableForStudent(int studentId) throws DAOException;
}
