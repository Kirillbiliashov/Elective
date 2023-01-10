package com.example.elective.services;

import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;

/**
 * Interface describing method that reads data from DB using DAO
 * @param <T> Return type parameter
 * @author Kirill Biliashov
 */

public interface DAOReadOperation<T> {

  T read() throws DAOException, MappingException;

}
