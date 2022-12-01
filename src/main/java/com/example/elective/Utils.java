package com.example.elective;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class Utils {

  public static final String ADMIN_SERVLET_NAME = "/elective/admin";
  public static final String TEACHER_SERVLET_NAME = "teacher";
  private static final String ZONE = "Europe/Paris";
  public static final Date CURRENT_DATE = Date.valueOf(LocalDate.now(ZoneId.of(ZONE)));
  private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

  public static int getIdFromPathInfo(String pathInfo) {
    return Integer.parseInt(pathInfo.substring(1));
  }

  public static boolean isNumeric(String str) {
    if (str == null) return false;
    return pattern.matcher(str).matches();
  }

}
