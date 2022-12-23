package com.example.elective.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Constants {

  public static final String ADMIN_SERVLET_NAME = "/elective/admin";
  private static final String ZONE = "Europe/Paris";
  public static final Date CURRENT_DATE = Date.valueOf(LocalDate.now(ZoneId.of(ZONE)));

}
