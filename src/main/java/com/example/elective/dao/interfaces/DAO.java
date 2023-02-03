package com.example.elective.dao.interfaces;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing CRUD operations on entity
 * @param <T> type parameter, subclass of Entity
 * @author Kirill Biliashov
 */

public interface DAO<T> {

  void setSession(Session session);

  List<T> getAll();

  Optional<T> find(int id);

  void save(T entity);

  void update(T entity);

  void delete(int id);

}
