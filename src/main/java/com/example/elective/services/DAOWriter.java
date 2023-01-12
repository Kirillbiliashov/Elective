package com.example.elective.services;

import com.example.elective.exceptions.DAOException;

/**
 * Interface describing method that reads data from DB using DAO
 * @author Kirill Biliashov
 */

public interface DAOWriter {

  void write() throws DAOException;

}
