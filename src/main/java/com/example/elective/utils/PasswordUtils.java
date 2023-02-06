package com.example.elective.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Class with utility methods regarding password hashing
 * @author Kirill Biliashov
 */

@Component
public class PasswordUtils {

  public String hash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public boolean match(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }

}
