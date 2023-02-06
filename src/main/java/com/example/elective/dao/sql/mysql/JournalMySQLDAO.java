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

  private static final String FIND_BY_COURSE = "SELECT j FROM Journal j where j.course = :course";

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
  public List<Journal> getByCourseId(int courseId) {
    Course course = session.byId(Course.class).load(courseId);
    return session
        .createQuery(FIND_BY_COURSE, Journal.class)
        .setParameter("course", course)
        .getResultList();
  }

}
