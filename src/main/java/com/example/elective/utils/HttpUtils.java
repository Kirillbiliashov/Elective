package com.example.elective.utils;

import com.example.elective.models.Account;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;

@Component
public class HttpUtils {

  public int getCurrentUserId(HttpServletRequest req) {
    HttpSession session = req.getSession();
    return ((Account) session.getAttribute(ACCOUNT_ATTR)).getId();
  }

}
