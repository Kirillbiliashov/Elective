package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import dto.JournalDTO;

import java.util.*;

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

  public List<JournalDTO> getJournalList(int courseId) throws ServiceException {
    transactionManager.initTransaction(accDao, journalDao);
    return performDaoReadOperation(() -> {
      List<JournalDTO> list = new ArrayList<>();
      List<Journal> journalList = journalDao.getByCourseId(courseId);
      for (final Journal journal : journalList) {
        JournalDTO dto = new JournalDTO();
        dto.setId(journal.getId());
        dto.setGrade(journal.getGrade());
        Account student = accDao.find(journal.getStudentId()).get();
        String name = student.getFirstName() + student.getLastName();
        dto.setStudent(name);
        list.add(dto);
      }
      return list;
    });
  }

}
