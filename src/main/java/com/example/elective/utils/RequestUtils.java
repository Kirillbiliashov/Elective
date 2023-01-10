package com.example.elective.utils;

import com.example.elective.models.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;

/**
 * Class with utility methods for getting ids
 * @author Kirill Biliashov
 */

public class RequestUtils {

  private static final int OFFSET = 1;

  public static int getIdFromPathInfo(String pathInfo) {
    return Integer.parseInt(pathInfo.substring(OFFSET));
  }

  public static int getCurrentUserId(HttpServletRequest req) {
    HttpSession session = req.getSession();
    return ((Account) session.getAttribute(ACCOUNT_ATTR)).getId();
  }
}
