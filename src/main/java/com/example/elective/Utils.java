package com.example.elective;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class Utils {

  public static final String ADMIN_REDIRECT_URL = "/elective/admin";
  public static final String TEACHER_REDIRECT_URL = "/elective/teacher";
  private static final String ZONE = "Europe/Paris";
  public static final Date CURRENT_DATE = Date.valueOf(LocalDate.now(ZoneId.of(ZONE)));
  private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

  public static int getIdFromPathInfo(String pathInfo) {
    int infoLength = pathInfo.length();
    return Integer.parseInt(pathInfo.substring(infoLength - 1, infoLength));
  }

  public static boolean isNumeric(String str) {
    if (str == null) return false;
    return pattern.matcher(str).matches();
  }


}
