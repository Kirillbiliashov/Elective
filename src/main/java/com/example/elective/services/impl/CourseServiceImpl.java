package com.example.elective.services.impl;

import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Account;
import com.example.elective.models.Topic;
import com.example.elective.selection.CourseSelection;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.models.Course;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.CourseService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Class containing business logic methods regarding courses
 *
 * @author Kirill Biliashov
 */

@Service
public class CourseServiceImpl extends AbstractService implements CourseService {

  @Override
  public void update(Course course, int teacherId, int topicId) {
    System.out.println("teacher id: " + teacherId);
    System.out.println("topic id: " + topicId);
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    Account teacher = session.byId(Account.class).load(teacherId);
    Topic topic = session.byId(Topic.class).load(topicId);
    course.setTeacher(teacher);
    course.setTopic(topic);
    dao.update(course);
    session.getTransaction().commit();
  }

  @Override
  public void save(Course course, int topicId, int teacherId) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    Topic topic = session.byId(Topic.class).load(topicId);
    Account teacher = session.byId(Account.class).load(teacherId);
    course.setTopic(topic);
    course.setTeacher(teacher);
    dao.save(course);
    session.getTransaction().commit();
  }

  @Override
  public void delete(int id) {
    CourseDAO dao = daoFactory.getCourseDAO();
    write(() -> dao.delete(id), dao);
  }

  @Override
  public Optional<Course> findById(int id) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.find(id), dao);
  }

  @Override
  public List<Course> getAll() {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(dao::getAll, dao);
  }

  @Override
  public List<Course> getAvailable(int studentId) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.getAvailableForStudent(studentId), dao);
  }

  @Override
  public List<Course> getCompletedCourses(int studentId) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.getCompletedForStudent(studentId), dao);
  }

  @Override
  public List<Course> getRegisteredCourses(int studentId) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.getRegisteredForStudent(studentId), dao);
  }

  @Override
  public List<Course> getCoursesInProgress(int studentId) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.getInProgressForStudent(studentId), dao);
  }

}