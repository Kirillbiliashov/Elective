package com.example.elective.services;

import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;

public interface DAOReadOperation<T> {

  T read() throws DAOException, MappingException;

}
