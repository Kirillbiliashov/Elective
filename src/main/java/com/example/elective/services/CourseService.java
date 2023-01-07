package com.example.elective.services;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.selection.CourseSelection;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
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

  public Optional<Course> findById(int id) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.find(id));
  }

  public List<CourseDTO> getBySelection(CourseSelection selection)
      throws ServiceException {
    transactionManager.initTransaction(accDAO, dao, topicDAO, journalDAO);
    List<CourseDTO> dtoList = performDaoReadOperation(() ->
        getCourseDTOList(dao.getAll()));
    return selection.getSelected(dtoList);
  }

  public List<CourseDTO> getAvailableBySelection(int studentId,
                                                 CourseSelection selection)
      throws ServiceException {
    transactionManager.initTransaction(accDAO, dao, topicDAO, journalDAO);
    List<CourseDTO> dtoList = performDaoReadOperation(() ->
        getCourseDTOList(dao.getAvailableForStudent(studentId)));
    return selection.getSelected(dtoList);
  }


  public List<CompletedCourseDTO> getCompletedCourses(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(dao, journalDAO, accDAO, topicDAO);
    return performDaoReadOperation(() -> {
      List<Course> courses = dao.getCompletedForStudent(studentId);
      return getCompletedCourseDTOList(courses, studentId);
    });
  }

  private List<CompletedCourseDTO> getCompletedCourseDTOList(List<Course> courses,
                                                             int studentId)
      throws DAOException, MappingException {
    List<CompletedCourseDTO> list = new ArrayList<>();
    for (final Course course : courses) {
      int grade = journalDAO
          .findByCourseAndStudent(course.getId(), studentId)
          .get()
          .getGrade();
      list.add(new CompletedCourseDTO(getCourseDTO(course), grade));
    }
    return list;
  }

  public List<RegisteredCourseDTO> getRegisteredCourses(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(dao, journalDAO, topicDAO, accDAO);
    return performDaoReadOperation(() -> {
      List<Course> courses = dao.getRegisteredForStudent(studentId);
      return getRegisteredCourseDTOList(courses, studentId);
    });
  }

  private List<RegisteredCourseDTO> getRegisteredCourseDTOList(List<Course> courses,
                                                               int studentId)
      throws DAOException, MappingException {
    List<RegisteredCourseDTO> list = new ArrayList<>();
    for (final Course course : courses) {
      Date date = journalDAO
          .findByCourseAndStudent(course.getId(), studentId)
          .get()
          .getEnrollmentDate();
      list.add(new RegisteredCourseDTO(getCourseDTO(course), date));
    }
    return list;
  }

  public List<CourseDTO> getCoursesInProgress(int studentId)
      throws ServiceException {
    transactionManager.initTransaction(dao, topicDAO, accDAO, journalDAO);
    return performDaoReadOperation(() ->
        getCourseDTOList(dao.getInProgressForStudent(studentId)));
  }

  private List<CourseDTO> getCourseDTOList(List<Course> courses)
      throws DAOException, MappingException {
    List<CourseDTO> list = new ArrayList<>();
    for (Course course : courses) list.add(getCourseDTO(course));
    return list;
  }

  private CourseDTO getCourseDTO(Course course) throws DAOException, MappingException {
    String topic = topicDAO.find(course.getTopicId()).get().getName();
    String name = accDAO.find(course.getTeacherId()).get().getFullName();
    int studentsCount = journalDAO.getStudentsCount(course.getId());
    CourseDTOMapper mapper = new CourseDTOMapper(name, topic, studentsCount);
    return mapper.map(course);
  }

}