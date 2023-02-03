package com.example.elective.services;

import com.example.elective.dao.DAOFactory;
import com.example.elective.dao.interfaces.DAO;
import com.example.elective.dao.sql.AbstractDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import org.hibernate.Session;

import java.util.function.Supplier;


/**
 * Abstract base class for all service classes that provides utility methods for them
 * @author Kirill Biliashov
 */

public abstract class AbstractService {

  protected DAOFactory daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL);

  protected void write(Runnable writeOperation, DAO... daos) {
    Session session = SQLDAOFactory.getSession();
    for (DAO dao: daos) dao.setSession(session);
    session.beginTransaction();
    writeOperation.run();
    session.getTransaction().commit();
  }

  protected <T> T read(Supplier<T> readOperation, DAO... daos) {
    Session session = SQLDAOFactory.getSession();
    for (DAO dao: daos) dao.setSession(session);
    session.beginTransaction();
    try {
      return readOperation.get();
    } finally {
      session.getTransaction().commit();
    }
  }

}
