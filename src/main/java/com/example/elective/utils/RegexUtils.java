package com.example.elective.utils;

import java.util.regex.Pattern;

/**
 * Class with utility method that checks input parameter for pattern
 * @author Kirill Biliashov
 */
public class RegexUtils {

  private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

  public static boolean isNumeric(String str) {
    if (str == null) return false;
    return pattern.matcher(str).matches();
  }

}
