package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.dtoMappers.CompletedCourseDTOMapper;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.mappers.dtoMappers.RegisteredCourseDTOMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.dto.CompletedCourseDTO;

import java.sql.Date;
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
        String topic = topicDao.find(course.getTopicId()).get().getName();
        Account teacher = accDao.find(course.getTeacherId()).get();
        String name = teacher.getFirstName() + " " + teacher.getLastName();
        int grade = journalDao.findByCourseAndStudent(course.getId(), studentId)
            .get().getGrade();
        CompletedCourseDTOMapper mapper = new CompletedCourseDTOMapper(name, topic, grade);
        list.add(mapper.map(course));
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
        String topic = topicDao.find(course.getTopicId()).get().getName();
        Account teacher = accDao.find(course.getTeacherId()).get();
        String name = teacher.getFirstName() + " " + teacher.getLastName();
        Journal journal = journalDao.findByCourseAndStudent(course.getId(), studentId).get();
        RegisteredCourseDTOMapper mapper = new RegisteredCourseDTOMapper(journal.getEnrollmentDate(), name, topic);
        list.add(mapper.map(course));
      }
      return list;
    });
  }

  public List<CourseDTO> getCoursesInProgress(int studentId) throws ServiceException {
    transactionManager.initTransaction(courseDao, topicDao, accDao);
    return performDaoReadOperation(() ->
        mapCoursesToDTO(courseDao.findInProgressForStudent(studentId)));
  }

  private List<CourseDTO> mapCoursesToDTO(List<Course> courses) throws DAOException,
      MappingException {
    List<CourseDTO> list = new ArrayList<>();
    for (Course course: courses) {
      String topic = topicDao.find(course.getTopicId()).get().getName();
      Account teacher = accDao.find(course.getTeacherId()).get();
      String name = teacher.getFirstName() + " " + teacher.getLastName();
      Mapper<Course, CourseDTO> mapper = new CourseDTOMapper(name, topic);
      list.add(mapper.map(course));
    }
    return list;
  }

}
