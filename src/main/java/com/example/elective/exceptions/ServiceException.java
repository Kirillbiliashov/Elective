package com.example.elective.exceptions;

/**
 * Exception Class that thrown in service class methods
 * @author Kirill Biliashov
 */

public class ServiceException extends Exception {

  public ServiceException(Throwable cause) {
    super(cause);
  }

}
