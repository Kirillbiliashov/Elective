package com.example.elective.dao.interfaces;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Entity;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DAO<T extends Entity> {

   void setConnection(Connection conn);

   List<T> getAll() throws DAOException;

   Optional<T> find(int id) throws DAOException;

   void save(T entity) throws DAOException;

   void update(T entity) throws DAOException;

   void delete(int id) throws DAOException;

}
