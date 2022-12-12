package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TeacherService extends AbstractService {

  private AccountDAO accDao = daoFactory.getAccountDAO();
  private CourseDAO courseDao = daoFactory.getCourseDAO();
  private JournalDAO journalDao = daoFactory.getJournalDAO();

  public int getPagesCount(int teacherId) throws ServiceException {
    transactionManager.initTransaction(courseDao);
    return performDaoReadOperation(() -> courseDao.getByTeacherId(teacherId).size());
  }

  public Optional<Course> getCourseAtPage(int teacherId, int page) throws ServiceException {
    transactionManager.initTransaction(courseDao);
    return performDaoReadOperation(() ->
        courseDao.getByTeacherIdAtPosition(teacherId, page));
  }

  public List<Account> getAll() throws ServiceException {
    transactionManager.initTransaction(accDao);
    return performDaoReadOperation(() -> accDao.findByRole("Teacher"));
  }

  public Map<Journal, Account> getJournalForCourse(int courseId) throws ServiceException {
    transactionManager.initTransaction(accDao, journalDao);
    return performDaoReadOperation(() -> {
      Map<Journal, Account> map = new LinkedHashMap<>();
      List<Journal> journalList = journalDao.getByCourseId(courseId);
      for (final Journal journal : journalList) {
        Account student = accDao.find(journal.getStudentId()).orElse(null);
        map.put(journal, student);
      }
      return map;
    });
  }

}
