package com.example.elective.utils;

import javax.servlet.http.HttpServletRequest;

public class PaginationUtils {

  public static void setPageAttributes(HttpServletRequest req, int currPage) {
    int nextPage = currPage + 1;
    int prevPage = currPage - 1;
    req.setAttribute("page", currPage);
    req.setAttribute("next", nextPage);
    req.setAttribute("prev", prevPage);
  }

  public static int getPageNumber(HttpServletRequest request) {
    String pageParam = request.getParameter("page");
    if (!RegexUtils.isNumeric(pageParam)) return 1;
    return Integer.parseInt(pageParam);
  }

}
