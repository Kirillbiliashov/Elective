package com.example.elective.dao;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Entity;

import java.util.List;
import java.util.Optional;

public interface DAO<T extends Entity> {

   List<T> findAll() throws DAOException;

   Optional<T> find(int id) throws DAOException;

   void save(T entity) throws DAOException;

   void update(T entity) throws DAOException;

   void delete(int id) throws DAOException;

}
