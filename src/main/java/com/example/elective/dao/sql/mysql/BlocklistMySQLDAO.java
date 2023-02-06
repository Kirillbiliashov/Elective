package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.dao.sql.AbstractDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Blocklist;

import java.util.List;
import java.util.Optional;

public class BlocklistMySQLDAO extends AbstractDAO implements BlocklistDAO {

  @Override
  public List<Blocklist> getAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Blocklist> find(int id) {
    Account student = session.byId(Account.class).load(id);
    return session
        .createQuery("SELECT b FROM Blocklist b WHERE b.student = :student", Blocklist.class)
        .setParameter("student", student)
        .getResultList()
        .stream()
        .findFirst();
  }

  @Override
  public void save(Blocklist blocklist) {
    session.persist(blocklist);
  }

  @Override
  public void update(Blocklist entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(int id) {
    Blocklist blocklist = session.byId(Blocklist.class).load(id);
    session.delete(blocklist);
  }

}
