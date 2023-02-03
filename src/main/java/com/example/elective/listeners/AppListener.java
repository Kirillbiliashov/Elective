package com.example.elective.listeners;

import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.services.impl.*;
import com.example.elective.services.interfaces.*;
import com.example.elective.utils.Constants;
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

  private static final Logger logger = LogManager.getLogger(AppListener.class);
  private static final BlocklistService BLOCKLIST_SERVICE = new BlocklistServiceImpl();
  private static final AccountService accService = new AccountServiceImpl();
  private static final JournalService journalService = new JournalServiceImpl();
  private static final TopicService topicService = new TopicServiceImpl();
  private static final TeacherService teacherService = new TeacherServiceImpl();
  private static final CourseService courseService = new CourseServiceImpl();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    setAttributes(context);
    logger.info("application has started");
  }

  private void setAttributes(ServletContext context) {
    context.setAttribute(ACCOUNT_SERVICE, accService);
    context.setAttribute(COURSE_SERVICE, courseService);
    context.setAttribute(JOURNAL_SERVICE, journalService);
    context.setAttribute(Constants.BLOCKLIST_SERVICE, BLOCKLIST_SERVICE);
    context.setAttribute(TEACHER_SERVICE, teacherService);
    context.setAttribute(TOPIC_SERVICE, topicService);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    SQLDAOFactory.closeFactory();
    logger.info("application has stopped");
  }

}
