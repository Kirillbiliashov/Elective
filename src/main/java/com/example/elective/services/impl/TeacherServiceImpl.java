package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.dto.JournalDTO;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.TeacherService;

import java.util.*;

/**
 * Class containing business logic methods regarding teachers
 * @author Kirill Biliashov
 */

public class TeacherServiceImpl extends AbstractService implements TeacherService {

  private final AccountService accService;

  public TeacherServiceImpl(AccountService accService) {
    this.accService = accService;
  }

  @Override
  public Optional<Course> findCourse(int teacherId, Pagination pagination)
      throws ServiceException {
    CourseDAO dao = daoFactory.getCourseDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.findByTeacherId(teacherId, pagination));
  }

  @Override
  public int getCoursesCount(int teacherId) throws ServiceException {
    CourseDAO dao = daoFactory.getCourseDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getCount(teacherId));
  }

  @Override
  public List<JournalDTO> getJournalList(int courseId) throws ServiceException {
    JournalDAO journalDao = daoFactory.getJournalDAO();
    TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(journalDao);
    return read(tm, () -> {
      List<Journal> journalList = journalDao.getByCourseId(courseId);
      List<JournalDTO> list = new ArrayList<>();
      String student;
      for (Journal journal : journalList) {
        student = accService.find(tm, journal.getStudentId()).get().getFullName();
        list.add(new JournalDTO(journal.getId(), journal.getGrade(), student));
      }
      return list;
    });
  }

}
