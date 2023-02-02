package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Journal;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.JournalService;
import org.hibernate.Session;

/**
 * Class containing business logic methods regarding journal
 * @author Kirill Biliashov
 */

public class JournalServiceImpl extends AbstractService implements JournalService {

  @Override
  public void save(Journal journal) {
    Session session = SQLDAOFactory.getSession();
    JournalDAO dao = daoFactory.getJournalDAO();
    dao.setSession(session);
    session.beginTransaction();
    dao.save(journal);
    session.getTransaction().commit();
  }

  @Override
  public void updateGrade(int id, int grade) {
    Session session = SQLDAOFactory.getSession();
    JournalDAO dao = daoFactory.getJournalDAO();
    dao.setSession(session);
    session.beginTransaction();
    dao.find(id).ifPresent(journal -> journal.setGrade(grade));
    session.getTransaction().commit();
  }

}
