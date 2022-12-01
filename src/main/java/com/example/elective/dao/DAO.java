package com.example.elective.dao;

import com.example.elective.models.Entity;

import java.util.List;
import java.util.Optional;

public interface DAO<T extends Entity> {

   List<T> findAll();

   Optional<T> find(int id);

   void save(T entity);

   void update(T entity);

   void delete(int id);

}
