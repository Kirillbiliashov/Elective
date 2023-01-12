package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.dto.JournalDTO;
import com.example.elective.selection.Pagination;

import java.util.*;

import static com.example.elective.utils.Constants.TEACHER_ROLE;

/**
 * Class containing business logic methods regarding teachers
 * @author Kirill Biliashov
 */

public class TeacherService extends AbstractService {

  private final AccountService accService;

  public TeacherService(AccountService accService) {
    this.accService = accService;
  }

  public Optional<Course> findCourse(int teacherId, Pagination pagination)
      throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = new TransactionManager();
    tm.initTransaction(dao);
    return read(tm, () -> dao.findByTeacherId(teacherId, pagination));
  }

  public int getCoursesCount(int teacherId) throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = new TransactionManager();
    tm.initTransaction(dao);
    return read(tm, () -> dao.getCount(teacherId));
  }

  public List<JournalDTO> getJournalList(int courseId) throws ServiceException {
    final JournalDAO journalDao = daoFactory.getJournalDAO();
    final TransactionManager tm = new TransactionManager();
    tm.initTransaction(journalDao);
    return read(tm, () -> {
      List<Journal> journalList = journalDao.getByCourseId(courseId);
      List<JournalDTO> list = new ArrayList<>();
      for (Journal journal : journalList) {
        String student = accService.find(tm, journal.getStudentId())
            .get().getFullName();
        JournalDTO dto = new JournalDTO(journal.getId(), journal.getGrade(),
            student);
        list.add(dto);
      }
      return list;
    });
  }

}
