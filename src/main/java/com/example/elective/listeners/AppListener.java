package com.example.elective.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class AppListener implements ServletContextListener {

  private final static Logger logger = LogManager.getLogger(AppListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    logger.info("application has started");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    logger.info("application has stopped");
  }

}
