package com.example.elective.services;

import com.example.elective.selection.CourseSelection;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dto.CompletedCourseDTO;
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
import com.example.elective.dto.CourseDTO;
import com.example.elective.models.Journal;

import java.sql.Date;
import java.util.*;

public class CourseService extends AbstractService {

  private final CourseDAO dao = daoFactory.getCourseDAO();
  private final AccountDAO accDAO = daoFactory.getAccountDAO();
  private final TopicDAO topicDAO = daoFactory.getTopicDAO();
  private final JournalDAO journalDAO = daoFactory.getJournalDAO();

  public void update(Course course) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.update(course));
  }

  public void save(Course course) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.save(course));
  }

  public void delete(int id) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.delete(id));
  }

  public Optional<Course> getById(int id) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.find(id));
  }

  public List<CourseDTO> getBySelection(CourseSelection selection) throws ServiceException {
    transactionManager.initTransaction(accDAO, dao, topicDAO, journalDAO);
    List<CourseDTO> dtoList = performDaoReadOperation(() -> getCourseDTOList(dao.findAll()));
    return selection.getSelected(dtoList);
  }

  public List<CourseDTO> getAvailableBySelection(int studentId,
                                                 CourseSelection selection)
      throws ServiceException {
    transactionManager.initTransaction(accDAO, dao, topicDAO, journalDAO);
    List<CourseDTO> dtoList = performDaoReadOperation(() ->
        getCourseDTOList(dao.findAvailableForStudent(studentId)));
    return selection.getSelected(dtoList);
  }


  public List<CompletedCourseDTO> getCompletedCourses(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(dao, journalDAO, accDAO, topicDAO);
    return performDaoReadOperation(() -> {
      List<Course> courses = dao.findCompletedForStudent(studentId);
      return getCompletedCourseDTOList(courses, studentId);
    });
  }

  private List<CompletedCourseDTO> getCompletedCourseDTOList(List<Course> courses,
                                                             int studentId)
      throws DAOException, MappingException {
    List<CompletedCourseDTO> list = new ArrayList<>();
    for (final Course course: courses) {
      String topic = topicDAO.find(course.getTopicId()).get().getName();
      Account teacher = accDAO.find(course.getTeacherId()).get();
      String name = teacher.getFirstName() + " " + teacher.getLastName();
      int studentsCount = journalDAO.getStudentsCount(course.getId());
      int grade = journalDAO.findByCourseAndStudent(course.getId(), studentId)
          .get().getGrade();
      CompletedCourseDTOMapper mapper = new CompletedCourseDTOMapper(name, topic, grade, studentsCount);
      list.add(mapper.map(course));
    }
    return list;
  }

  public List<RegisteredCourseDTO> getRegisteredCourses(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(dao, journalDAO, topicDAO, accDAO);
    return performDaoReadOperation(() -> {
      List<Course> courses = dao.findRegisteredForStudent(studentId);
      return getRegisteredCourseDTOList(courses, studentId);
    });
  }

  private List<RegisteredCourseDTO> getRegisteredCourseDTOList(List<Course> courses,
                                                               int studentId)
      throws DAOException, MappingException {
    List<RegisteredCourseDTO> list = new ArrayList<>();
    for (final Course course: courses) {
      String topic = topicDAO.find(course.getTopicId()).get().getName();
      Account teacher = accDAO.find(course.getTeacherId()).get();
      String name = teacher.getFirstName() + " " + teacher.getLastName();
      int studentsCount = journalDAO.getStudentsCount(course.getId());
      Journal journal = journalDAO.findByCourseAndStudent(course.getId(), studentId).get();
      Date date = journal.getEnrollmentDate();
      RegisteredCourseDTOMapper mapper = new RegisteredCourseDTOMapper(date, name, topic, studentsCount);
      list.add(mapper.map(course));
    }
    return list;
  }

  public List<CourseDTO> getCoursesInProgress(int studentId) throws ServiceException {
    transactionManager.initTransaction(dao, topicDAO, accDAO, journalDAO);
    return performDaoReadOperation(() ->
        getCourseDTOList(dao.findInProgressForStudent(studentId)));
  }

  private List<CourseDTO> getCourseDTOList(List<Course> courses) throws DAOException,
      MappingException {
    List<CourseDTO> list = new ArrayList<>();
    for (Course course: courses) {
      String topic = topicDAO.find(course.getTopicId()).get().getName();
      Account teacher = accDAO.find(course.getTeacherId()).get();
      String name = teacher.getFirstName() + " " + teacher.getLastName();
      int studentsCount = journalDAO.getStudentsCount(course.getId());
      Mapper<Course, CourseDTO> mapper = new CourseDTOMapper(name, topic, studentsCount);
      list.add(mapper.map(course));
    }
    return list;
  }

}