package com.example.elective.dao.sql.mysql;

import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.dao.sql.AbstractDAO;
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
    return Optional.of(session.get(Blocklist.class, id));
  }

  @Override
  public void save(Blocklist blocklist) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(Blocklist entity) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(int studentId) {
    session.remove(studentId);
  }

  @Override
  public void save(int studentId) {
    session.persist(studentId);
  }

}
