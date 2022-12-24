package com.example.elective.utils;

import com.example.elective.models.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtils {

  public static int getIdFromPathInfo(String pathInfo) {
    return Integer.parseInt(pathInfo.substring(1));
  }

  public static int getCurrentUserId(HttpServletRequest req) {
    HttpSession session = req.getSession();
    return ((Account) session.getAttribute("account")).getId();
  }

  public static String getRedirectUrl(HttpServletRequest req, String url) {
    String currLang = req.getParameter("lang");
    String langQueryParam = currLang == null ? "?lang=en" : "?lang=" + currLang;
    return url + langQueryParam;
  }

}