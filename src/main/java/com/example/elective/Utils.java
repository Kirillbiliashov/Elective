package com.example.elective;

public class Utils {

  public static final String ADMIN_REDIRECT_URL = "/elective/admin";

  public static int getIdFromPathInfo(String pathInfo) {
    int infoLength = pathInfo.length();
    return Integer.parseInt(pathInfo.substring(infoLength - 1, infoLength));
  }

}
