package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentService extends AbstractService {

  private AccountDAO accDao = daoFactory.getAccountDAO();
  private CourseDAO courseDao = daoFactory.getCourseDAO();
  private JournalDAO journalDao = daoFactory.getJournalDAO();

  public void changeBlockStatus(int id) throws ServiceException {
    transactionManager.initTransaction(accDao);
    performDaoWriteOperation(() -> {
      Optional<Account> optAcc = accDao.find(id);
      if (optAcc.isPresent()) {
        Account acc = optAcc.get();
        acc.getBuilder().setBlocked(!acc.isBlocked());
        accDao.update(acc);
      }
    });
  }

  public List<Account> getAll() throws ServiceException {
    transactionManager.initTransaction(accDao);
    return performDaoReadOperation(() -> accDao.findByRole("Student"));
  }

  public Map<Course, Journal> getCompletedCoursesWithGrades(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(courseDao, journalDao);
    return performDaoReadOperation(() -> {
      List<Course> courses = courseDao.findCompletedForStudent(studentId);
      return getCourseJournalMap(courses, studentId);
    });
  }

  public List<Course> getAvailableCourses(int studentId) throws ServiceException {
    transactionManager.initTransaction(courseDao);
    return performDaoReadOperation(() ->
        courseDao.findAvailableForStudent(studentId));
  }

  public Map<Course, Journal> getRegisteredCoursesMap(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(courseDao, journalDao);
    return performDaoReadOperation(() -> {
      List<Course> courses = courseDao.findRegisteredForStudent(studentId);
      return getCourseJournalMap(courses, studentId);
    });
  }

  private Map<Course, Journal> getCourseJournalMap(List<Course> courses,
                                                   int studentId)
      throws DAOException {
    Map<Course, Journal> map = new LinkedHashMap<>();
    for (final Course course: courses) {
      map.put(course, journalDao
          .findByCourseAndStudent(course.getId(), studentId)
          .orElse(null));
    }
    return map;
  }

  public List<Course> getCoursesInProgress(int studentId) throws ServiceException {
    transactionManager.initTransaction(courseDao);
    return performDaoReadOperation(() ->
        courseDao.findInProgressForStudent(studentId));
  }

}
