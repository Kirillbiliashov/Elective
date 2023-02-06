package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.sql.AbstractDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Class that implements methods of CourseDAO for MySQL database
 * @author Kirill Biliashov
 */

public class CourseMySQLDAO extends AbstractDAO implements CourseDAO {

  private static final String GET_ALL = "SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.students";
  private static final String GET_COMPLETED_FOR_STUDENT = "SELECT c FROM Course c LEFT JOIN FETCH c.students s " +
      "WHERE s.student = :student AND c.endDate < CURRENT_DATE";
  private static final String GET_IN_PROGRESS_FOR_STUDENT = "SELECT c FROM Course c LEFT JOIN FETCH c.students s" +
      " WHERE s.student = :student AND CURRENT_DATE BETWEEN c.startDate AND c.endDate";
  private static final String GET_REGISTERED_FOR_STUDENT = "SELECT c FROM Course c LEFT JOIN FETCH c.students s " +
      "WHERE s.student = :student AND c.startDate > CURRENT_DATE";
  private static final String GET_AVAILABLE_FOR_STUDENT = "SELECT c FROM Course c LEFT JOIN FETCH c.students s " +
      "WHERE c.id != ALL (SELECT c.id FROM Course c WHERE s.student = :student)" +
      " AND c.startDate > CURRENT_DATE";
  private static final String FIND_BY_TEACHER = "SELECT c FROM Course c " +
      "WHERE c.teacher = :teacher";
  private static final String GET_COUNT = "SELECT COUNT(c) FROM Course c";

  @Override
  public Optional<Course> findByTeacherId(int teacherId, Pagination pagination) {
    Account teacher = session.byId(Account.class).load(teacherId);
    return Optional.ofNullable(session
        .createQuery(FIND_BY_TEACHER, Course.class)
        .setParameter("teacher", teacher)
        .setFirstResult(pagination.getFrom())
        .setMaxResults(pagination.getDisplayCount())
        .getSingleResult());
  }

  @Override
  public void update(Course course) {
    session.update(course);
  }

  @Override
  public void save(Course course) {
    session.persist(course);
  }

  @Override
  public void delete(int id) {
    Course course = session.byId(Course.class).load(id);
    session.remove(course);
  }

  @Override
  public List<Course> getAll() {
    return session
        .createQuery(GET_ALL, Course.class)
        .getResultList();
  }

  @Override
  public Optional<Course> find(int id) {
    return Optional.ofNullable(session.get(Course.class, id));
  }

  @Override
  public List<Course> getCompletedForStudent(int studentId) {
    return getByStudentId(studentId, GET_COMPLETED_FOR_STUDENT);
  }

  @Override
  public List<Course> getInProgressForStudent(int studentId) {
    return getByStudentId(studentId, GET_IN_PROGRESS_FOR_STUDENT);
  }

  @Override
  public List<Course> getRegisteredForStudent(int studentId) {
    return getByStudentId(studentId, GET_REGISTERED_FOR_STUDENT);
  }

  @Override
  public List<Course> getAvailableForStudent(int studentId) {
    return getByStudentId(studentId, GET_AVAILABLE_FOR_STUDENT);
  }

  @Override
  public long getCount(int teacherId) {
    return session
        .createQuery(GET_COUNT, Long.class)
        .getSingleResult();
  }

  private List<Course> getByStudentId(int studentId, String query) {
    Account student = session.byId(Account.class).load(studentId);
    return session
        .createQuery(query, Course.class)
        .setParameter("student", student)
        .getResultList();
  }

}
