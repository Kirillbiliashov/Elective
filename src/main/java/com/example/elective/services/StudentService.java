package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.dto.CompletedCourseDTO;

import java.util.*;

public class StudentService extends AbstractService {

  private AccountDAO accDao = daoFactory.getAccountDAO();
  private CourseDAO courseDao = daoFactory.getCourseDAO();
  private JournalDAO journalDao = daoFactory.getJournalDAO();
  private TopicDAO topicDao = daoFactory.getTopicDAO();

  public void changeBlockStatus(int id) throws ServiceException {
    transactionManager.initTransaction(accDao);
    performDaoWriteOperation(() -> {
      Optional<Account> optAcc = accDao.find(id);
      if (optAcc.isPresent()) {
        Account acc = optAcc.get();
        acc.getBuilder().setBlocked(!acc.isBlocked());
        accDao.update(acc);
      }
    });
  }

  public List<Account> getAll() throws ServiceException {
    transactionManager.initTransaction(accDao);
    return performDaoReadOperation(() -> accDao.findByRole("Student"));
  }

  public List<CompletedCourseDTO> getCompletedCourses(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(courseDao, journalDao, accDao, topicDao);
    return performDaoReadOperation(() -> {
      List<Course> courses = courseDao.findCompletedForStudent(studentId);
      List<CompletedCourseDTO> list = new ArrayList<>();
      for (final Course course: courses) {
        CompletedCourseDTO dto = new CompletedCourseDTO();
        populateCourseDTO(course, dto);
        int grade = journalDao.findByCourseAndStudent(course.getId(), studentId)
            .get().getGrade();
        dto.setGrade(grade);
        list.add(dto);
      }
      return list;
    });
  }

  public List<CourseDTO> getAvailableCourses(int studentId) throws ServiceException {
    transactionManager.initTransaction(courseDao, topicDao, accDao);
    return performDaoReadOperation(() ->
        mapCoursesToDTO(courseDao.findAvailableForStudent(studentId)));
  }

  public List<RegisteredCourseDTO> getRegisteredCourses(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(courseDao, journalDao, topicDao, accDao);
    return performDaoReadOperation(() -> {
      List<Course> courses = courseDao.findRegisteredForStudent(studentId);
      List<RegisteredCourseDTO> list = new ArrayList<>();
      for (final Course course: courses) {
        RegisteredCourseDTO dto = new RegisteredCourseDTO();
        populateCourseDTO(course, dto);
        Journal journal = journalDao.findByCourseAndStudent(course.getId(), studentId).get();
        dto.setRegistrationDate(journal.getEnrollmentDate());
        list.add(dto);
      }
      return list;
    });
  }

  public List<CourseDTO> getCoursesInProgress(int studentId) throws ServiceException {
    transactionManager.initTransaction(courseDao, topicDao, accDao);
    return performDaoReadOperation(() ->
        mapCoursesToDTO(courseDao.findInProgressForStudent(studentId)));
  }

  private List<CourseDTO> mapCoursesToDTO(List<Course> courses) throws DAOException {
    List<CourseDTO> list = new ArrayList<>();
    for (Course course: courses) {
      CourseDTO dto = new CourseDTO();
      populateCourseDTO(course, dto);
      list.add(dto);
    }
    return list;
  }

  private void populateCourseDTO(Course course, CourseDTO dto) throws DAOException {
    dto.setId(course.getId());
    dto.setName(course.getName());
    dto.setDescription(course.getDescription());
    dto.setStartDate(course.getStartDate());
    dto.setEndDate(course.getEndDate());
    String topic = topicDao.find(course.getTopicId()).get().getName();
    dto.setTopic(topic);
    Account teacher = accDao.find(course.getTeacherId()).get();
    String name = teacher.getFirstName() + " " + teacher.getLastName();
    dto.setTeacher(name);
  }

}
