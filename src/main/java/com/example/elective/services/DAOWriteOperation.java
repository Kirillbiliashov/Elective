package com.example.elective.services;

import com.example.elective.exceptions.DAOException;

public interface DAOWriteOperation {

  void write() throws DAOException;

}
