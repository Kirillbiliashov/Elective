package com.example.elective.services;

import com.example.elective.exceptions.DAOException;

public interface DAOReadOperation<T> {

  T read() throws DAOException;

}
