package com.example.elective.commands;

import com.example.elective.models.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;

public abstract class Command {

  private static final int OFFSET = 1;

  protected ServletContext context;
  protected HttpServletRequest req;
  protected HttpServletResponse resp;

  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    this.context = context;
    this.req = req;
    this.resp = resp;
  }

  public abstract void process() throws ServletException, IOException;

  protected void forward(String target) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher(target);
    dispatcher.forward(req, resp);
  }

  protected int getIdFromPathInfo() {
    String pathInfo = req.getPathInfo();
    return Integer.parseInt(pathInfo.substring(OFFSET));
  }

  protected int getCurrentUserId() {
    HttpSession session = req.getSession();
    return ((Account) session.getAttribute(ACCOUNT_ATTR)).getId();
  }

}
