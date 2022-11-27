package com.example.elective.dao;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> {

  public abstract List<T> findAll();

  public abstract Optional<T> find(int id);

  public abstract void save(T entity);

  public abstract void update(T entity);

  public abstract void delete(int id);

}
