package com.example.elective.services;

import com.example.elective.TransactionManager;
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
    int pagesCount = courseDao.getByTeacherId(teacherId).size();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return pagesCount;
  }

  public List<Account> getAll() {
    transactionManager.initTransaction(accDao);
    List<Account> accountList = accDao.getByRole("Teacher");
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return accountList;
  }

  public Map.Entry<Course, Map<Journal, Account>> getJournal(int teacherId, int page) {
    transactionManager.initTransaction(courseDao, accDao, journalDao);
    Optional<Course> optCourse = courseDao.getByTeacherIdAtPosition(teacherId, page);
    if (!optCourse.isPresent()) return null;
    Course course = optCourse.get();
    Map.Entry<Course, Map<Journal, Account>> entry =
        Map.entry(course, getJournalStudent(course.getId()));
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return entry;
  }

  private Map<Journal, Account> getJournalStudent(int courseId) {
    Map<Journal, Account> map = new LinkedHashMap<>();
    List<Journal> journalList = journalDao.getByCourseId(courseId);
    for (final Journal journal: journalList) {
      int studentId = journal.getStudentId();
      Account student = accDao.find(studentId).orElse(null);
      map.put(journal, student);
    }
    return map;
  }

}
