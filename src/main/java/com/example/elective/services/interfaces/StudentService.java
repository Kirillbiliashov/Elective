package com.example.elective.services.interfaces;

import com.example.elective.exceptions.ServiceException;

public interface StudentService {

  void changeBlockStatus(int id) throws ServiceException;

}
