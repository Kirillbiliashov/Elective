package com.example.elective.utils;

import javax.servlet.http.HttpServletRequest;

import static com.example.elective.utils.Constants.*;

/**
 * Class with utility methods related to pagination
 * @author Kirill Biliashov
 */
public class PaginationUtils {

  private static final int DEFAULT_PAGE = 1;
  private static final int DEFAULT_DISPLAY = 5;

  public static void setPageAttributes(HttpServletRequest req, int currPage) {
    int nextPage = currPage + 1;
    int prevPage = currPage - 1;
    req.setAttribute(PAGE_ATTR, currPage);
    req.setAttribute(NEXT_ATTR, nextPage);
    req.setAttribute(PREV_ATTR, prevPage);
  }

  public static int getPageNumber(HttpServletRequest request) {
    String pageParam = request.getParameter(PAGE_ATTR);
    if (!RegexUtils.isNumeric(pageParam)) return DEFAULT_PAGE;
    return Integer.parseInt(pageParam);
  }

  public static int getItemsPerPage(HttpServletRequest request) {
    String pageParam = request.getParameter(DISPLAY_PARAM);
    if (!RegexUtils.isNumeric(pageParam)) return DEFAULT_DISPLAY;
    return Integer.parseInt(pageParam);
  }

}
