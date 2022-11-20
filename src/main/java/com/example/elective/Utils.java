package com.example.elective;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Utils {

  public static final String ADMIN_REDIRECT_URL = "/elective/admin";
  public static final String TEACHER_REDIRECT_URL = "/elective/teacher";
  private static final String ZONE = "Europe/Paris";
  public static final Date CURRENT_DATE = Date.valueOf(LocalDate.now(ZoneId.of(ZONE)));

  public static int getIdFromPathInfo(String pathInfo) {
    int infoLength = pathInfo.length();
    return Integer.parseInt(pathInfo.substring(infoLength - 1, infoLength));
  }

}
