package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.dto.JournalDTO;
import com.example.elective.selection.Pagination;

import java.util.*;

import static com.example.elective.utils.Constants.TEACHER_ROLE;

public class TeacherService extends AbstractService {

  private final AccountDAO accDao = daoFactory.getAccountDAO();
  private final CourseDAO courseDao = daoFactory.getCourseDAO();
  private final JournalDAO journalDao = daoFactory.getJournalDAO();

  public Optional<Course> findCourse(int teacherId, Pagination pagination)
      throws ServiceException {
    transactionManager.initTransaction(courseDao);
    return performDaoReadOperation(() ->
        courseDao.findByTeacherId(teacherId, pagination));
  }

  public int getCoursesCount(int teacherId) throws ServiceException {
    transactionManager.initTransaction(courseDao);
    return performDaoReadOperation(() -> courseDao.getCount(teacherId));
  }

  public List<Account> getAll() throws ServiceException {
    transactionManager.initTransaction(accDao);
    return performDaoReadOperation(() -> accDao.getByRole(TEACHER_ROLE));
  }

  public List<JournalDTO> getJournalList(int courseId) throws ServiceException {
    transactionManager.initTransaction(accDao, journalDao);
    return performDaoReadOperation(() -> {
      List<Journal> journalList = journalDao.getByCourseId(courseId);
      List<JournalDTO> list = new ArrayList<>();
      for (final Journal journal : journalList) addDTOToList(list, journal);
      return list;
    });
  }

  private void addDTOToList(List<JournalDTO> list, Journal journal)
      throws DAOException {
    String student = accDao.find(journal.getStudentId()).get().getFullName();
    JournalDTO dto = new JournalDTO(journal.getId(), journal.getGrade(),
        student);
    list.add(dto);
  }

}
