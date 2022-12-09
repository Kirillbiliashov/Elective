package com.example.elective;

import com.example.elective.models.Account;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {

  public static final String ADMIN_SERVLET_NAME = "/elective/admin";
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

  public static int getCurrentUserId(HttpServletRequest req) {
    HttpSession session = req.getSession();
    return ((Account) session.getAttribute("account")).getId();
  }

  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean passwordsMatch(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }

  public static String getRedirectUrl(HttpServletRequest req, String url) {
    String currLang = req.getParameter("lang");
    String langQueryParam = currLang == null ? "?lang=en" : "?lang=" + currLang;
    return url + langQueryParam;
  }

}
