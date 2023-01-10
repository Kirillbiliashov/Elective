package com.example.elective.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Class with utility methods regarding password hashing
 * @author Kirill Biliashov
 */

public class PasswordUtils {

  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean passwordsMatch(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }

}
