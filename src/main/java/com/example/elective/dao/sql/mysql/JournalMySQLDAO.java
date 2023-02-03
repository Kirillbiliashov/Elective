package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.AbstractDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;

import java.util.List;
import java.util.Optional;

/**
 * Class that implements methods of JournalDAO for MySQL database
 * @author Kirill Biliashov
 */

public class JournalMySQLDAO extends AbstractDAO implements JournalDAO {

  private static final String FIND_BY_STUDENT = "SELECT j FROM Journal j where " +
      "j.course = :course AND j.student = :student";
  private static final String FIND_BY_COURSE = "SELECT j FROM Journal j where j.course = :course";
  private static final String GET_STUDENTS_COUNT = "SELECT COUNT(j) FROM Journal j WHERE j.course = :course";

  @Override
  public Optional<Journal> find(int journalId) {
    return Optional.ofNullable(session.get(Journal.class, journalId));
  }

  @Override
  public void update(Journal journal) {
    session.persist(journal);
  }

  @Override
  public void save(Journal journal) {
    session.persist(journal);
  }

  @Override
  public void delete(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Journal> getAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Journal> findByCourseAndStudent(int courseId, int studentId) {
    Course course = session.byId(Course.class).load(courseId);
    Account student = session.byId(Account.class).load(studentId);
    return Optional.ofNullable(session
        .createQuery(FIND_BY_STUDENT, Journal.class)
        .setParameter("course", course)
        .setParameter("student", student)
        .getSingleResult());
  }

  @Override
  public List<Journal> getByCourseId(int courseId) {
    Course course = session.byId(Course.class).load(courseId);
    return session
        .createQuery(FIND_BY_COURSE, Journal.class)
        .setParameter("course", course)
        .getResultList();
  }

  @Override
  public int getStudentsCount(int courseId) {
    Course course = session.byId(Course.class).load(courseId);
    return session
        .createQuery(GET_STUDENTS_COUNT, Integer.class)
        .setParameter("course", course)
        .getSingleResult();
  }

}
