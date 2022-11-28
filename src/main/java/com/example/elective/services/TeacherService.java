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

  public int getPagesCount(int teacherId) {
    return courseDao.getByTeacherId(teacherId).size();
  }

  public List<Account> getAll() {
    return accDao.getByRole("Teacher");
  }

  public Map.Entry<Course, Map<Journal, Account>> getJournal(int teacherId, int page) {
    List<Course> courses = courseDao.getByTeacherId(teacherId);
    Course course = courses.get(page - 1);
    return Map.entry(course, getJournalStudent(course.getId()));
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
