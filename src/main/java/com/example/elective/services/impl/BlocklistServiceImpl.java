package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Blocklist;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.BlocklistService;
import org.hibernate.Session;

import java.util.*;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

public class BlocklistServiceImpl extends AbstractService implements BlocklistService {

  @Override
  public void changeBlockStatus(int id) {
    Session session = SQLDAOFactory.getSession();
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    dao.setSession(session);
    session.beginTransaction();
    Optional<Blocklist> optBlocklist = dao.find(id);
    if (optBlocklist.isPresent()) dao.delete(id);
    else dao.save(id);
    session.getTransaction().commit();
  }

  @Override
  public Optional<Blocklist> getBlockStatus(int id) {
    Session session = SQLDAOFactory.getSession();
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    dao.setSession(session);
    session.beginTransaction();
    try {
      return dao.find(id);
    } finally {
      session.getTransaction().commit();
    }
  }

}
