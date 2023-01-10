package com.example.elective.listeners;

import com.example.elective.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.example.elective.utils.Constants.*;

/**
 * Class that listens to application lifecycle events such as start/stop
 * @author Kirill Biliashov
 */

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
    context.setAttribute(ACCOUNT_SERVICE, new AccountService());
    context.setAttribute(COURSE_SERVICE, new CourseService());
    context.setAttribute(JOURNAL_SERVICE, new JournalService());
    context.setAttribute(STUDENT_SERVICE, new StudentService());
    context.setAttribute(TEACHER_SERVICE, new TeacherService());
    context.setAttribute(TOPIC_SERVICE, new TopicService());
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    logger.info("application has stopped");
  }

}
