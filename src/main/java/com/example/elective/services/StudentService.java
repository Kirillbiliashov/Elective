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

public class StudentService extends AbstractService {

  private AccountDAO accDao = new AccountDAO();
  private CourseDAO courseDao = new CourseDAO();
  private JournalDAO journalDao = new JournalDAO();

  public void changeBlockStatus(int id) {
    transactionManager.initTransaction(accDao);
    performWriteOperation(() -> {
      Optional<Account> optAcc = accDao.find(id);
      if (optAcc.isPresent()) {
        Account acc = optAcc.get();
        acc.getBuilder().setBlocked(!acc.isBlocked());
        accDao.update(acc);
      }
    });
  }

  public List<Account> getAll() {
    transactionManager.initTransaction(accDao);
    return performReadOperation(() -> accDao.getByRole("Student"));
  }

  public Map<Course, Journal> getCourseJournal(int studentId) {
    transactionManager.initTransaction(courseDao, journalDao);
    return performReadOperation(() -> {
      List<Course> courses = courseDao.findAll();
      Map<Course, Journal> map = new LinkedHashMap<>();
      for (final Course course: courses) {
        map.put(course, journalDao
            .findByCourseAndStudent(course.getId(), studentId)
            .orElse(null));
      }
      return map;
    });
  }

}
