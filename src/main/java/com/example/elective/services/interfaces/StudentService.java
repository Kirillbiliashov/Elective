package com.example.elective.services.interfaces;

import com.example.elective.exceptions.ServiceException;

/**
 * Interface describing methods that student service should implement
 * @author Kirill Biliashov
 */

public interface StudentService {

  void changeBlockStatus(int id) throws ServiceException;

}
