package com.example.elective.listeners;

import com.example.elective.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

  private final static Logger logger = LogManager.getLogger(AppListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    setAttributes(context);
    logger.info("application has started");
  }

  private void setAttributes(ServletContext context) {
    context.setAttribute("accountService", new AccountService());
    context.setAttribute("courseService", new CourseService());
    context.setAttribute("journalService", new JournalService());
    context.setAttribute("roleService", new RoleService());
    context.setAttribute("studentService", new StudentService());
    context.setAttribute("teacherService", new TeacherService());
    context.setAttribute("topicService", new TopicService());
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    logger.info("application has stopped");
  }

}
