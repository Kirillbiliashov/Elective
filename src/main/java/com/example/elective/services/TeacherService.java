package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TeacherService extends AbstractService {

  private AccountDAO accDao = new AccountDAO();
  private CourseDAO courseDao = new CourseDAO();
  private JournalDAO journalDao = new JournalDAO();

  public int getPagesCount(int teacherId) {
    transactionManager.initTransaction(courseDao);
    return performReadOperation(() -> courseDao.getByTeacherId(teacherId).size());
  }

  public Optional<Course> getCourseAtPage(int teacherId, int page) {
    transactionManager.initTransaction(courseDao);
    return performReadOperation(() ->
        courseDao.getByTeacherIdAtPosition(teacherId, page));
  }

  public List<Account> getAll() {
    transactionManager.initTransaction(accDao);
    return performReadOperation(() -> accDao.getByRole("Teacher"));
  }

  public Map<Journal, Account> getJournalForCourse(int courseId) {
    transactionManager.initTransaction(accDao, journalDao);
    return performReadOperation(() -> {
      Map<Journal, Account> map = new LinkedHashMap<>();
      List<Journal> journalList = journalDao.getByCourseId(courseId);
      for (final Journal journal: journalList) {
        Account student = accDao.find(journal.getStudentId()).orElse(null);
        map.put(journal, student);
      }
      return map;
    });
  }

}
