package com.example.elective.utils;

import java.util.regex.Pattern;

public class RegexUtils {

  private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

  public static boolean isNumeric(String str) {
    if (str == null) return false;
    return pattern.matcher(str).matches();
  }

}
