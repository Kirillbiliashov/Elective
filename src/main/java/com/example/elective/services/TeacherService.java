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

public class TeacherService {

  private AccountDAO accDao = new AccountDAO();
  private CourseDAO courseDao = new CourseDAO();
  private JournalDAO journalDao = new JournalDAO();

  public List<Account> getAll() {
    return accDao.getByRole("Teacher");
  }

  public Map<Course, Map<Journal, Account>> getJournal(int teacherId) {
    Map<Course, Map<Journal, Account>> map = new LinkedHashMap<>();
    List<Course> courses = courseDao.getByTeacherId(teacherId);
    for (final Course course: courses) {
      map.put(course, getJournalStudent(course.getId()));
    }
    return map;
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
