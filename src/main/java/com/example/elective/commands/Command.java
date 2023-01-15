package com.example.elective.commands;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

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

}
