package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.utils.Constants;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 * Class containing business logic methods regarding journal
 * @author Kirill Biliashov
 */

@Service
public class JournalServiceImpl extends AbstractService implements JournalService {

  @Override
  public void save(int courseId, int studentId) {
    Session session = SQLDAOFactory.getSession();
    JournalDAO dao = daoFactory.getJournalDAO();
    Journal journal = new Journal().setEnrollmentDate(Constants.CURRENT_DATE);
    dao.setSession(session);
    session.beginTransaction();
    Course course = session.byId(Course.class).load(courseId);
    Account student = session.byId(Account.class).load(studentId);
    journal.setCourse(course);
    journal.setStudent(student);
    dao.save(journal);
    session.getTransaction().commit();
  }

  @Override
  public void updateGrade(int id, int grade) {
    JournalDAO dao = daoFactory.getJournalDAO();
    write(() -> dao.find(id).ifPresent(journal -> journal.setGrade(grade)), dao);
  }

}
