package com.example.elective;

public class Utils {

  public static int getIdFromPathInfo(String pathInfo) {
    int infoLength = pathInfo.length();
    return Integer.parseInt(pathInfo.substring(infoLength - 1, infoLength));
  }

}
