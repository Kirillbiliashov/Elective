package com.example.elective.exceptions;

/**
 * Exception class thrown in DAO methods
 * @author Kirill Biliashov
 */

public class DAOException extends Exception {

  public DAOException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
