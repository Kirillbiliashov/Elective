package com.example.elective.services.impl;

import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Journal;
import com.example.elective.selection.CourseSelection;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.models.Course;
import com.example.elective.dto.CourseDTO;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.CourseService;
import org.hibernate.Session;

import java.sql.Date;
import java.util.*;

/**
 * Class containing business logic methods regarding courses
 *
 * @author Kirill Biliashov
 */

public class CourseServiceImpl extends AbstractService implements CourseService {

  private final CourseDTOMapper mapper = new CourseDTOMapper();

  @Override
  public void update(Course course) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    dao.update(course);
    session.getTransaction().commit();
  }

  @Override
  public void save(Course course) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    dao.save(course);
    session.getTransaction().commit();
  }

  @Override
  public void delete(int id) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    dao.delete(id);
    session.getTransaction().commit();
  }

  @Override
  public Optional<Course> findById(int id) {
    Session session  = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    try {
      return dao.find(id);
    } finally {
      session.getTransaction().commit();
    }
  }

  @Override
  public List<CourseDTO> getBySelection(CourseSelection selection) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    List<Course> courses = dao.getAll();
    session.getTransaction().commit();
    return selection.getSelected(courses.stream().map(mapper::map).toList());
  }

  @Override
  public List<CourseDTO> getAvailableBySelection(
      int studentId, CourseSelection selection) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    List<Course> courses = dao.getAvailableForStudent(studentId);
    session.getTransaction().commit();
    return selection.getSelected(courses.stream().map(mapper::map).toList());
  }

  @Override
  public List<CompletedCourseDTO> getCompletedCourses(int studentId) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    List<Course> courses = dao.getCompletedForStudent(studentId);
    session.getTransaction().commit();
    return courses.stream().map(course -> {
      int grade = course
          .getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get()
          .getGrade();
      return new CompletedCourseDTO(mapper.map(course), grade);
    }).toList();
  }

  @Override
  public List<RegisteredCourseDTO> getRegisteredCourses(int studentId) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    List<Course> courses = dao.getRegisteredForStudent(studentId);
    session.getTransaction().commit();
    return courses.stream().map(course -> {
      Journal studentCourse = course
          .getStudents()
          .stream()
          .filter(e -> e.getStudent().getId() == studentId)
          .findFirst()
          .get();
      Date registrationDate = studentCourse.getEnrollmentDate();
      return new RegisteredCourseDTO(mapper.map(course), registrationDate);
    }).toList();
  }

  @Override
  public List<CourseDTO> getCoursesInProgress(int studentId) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    List<Course> courses = dao.getInProgressForStudent(studentId);
    session.getTransaction().commit();
    return courses.stream().map(mapper::map).toList();
  }

}