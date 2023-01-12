package com.example.elective.services;

import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.selection.CourseSelection;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dto.CompletedCourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.models.Course;
import com.example.elective.dto.CourseDTO;

import java.sql.Date;
import java.util.*;

/**
 * Class containing business logic methods regarding courses
 *
 * @author Kirill Biliashov
 */

public class CourseService extends AbstractService {

  private final TopicService topicService;
  private final AccountService accService;
  private final JournalService journalService;

  public CourseService(TopicService topicService,
                       AccountService accService,
                       JournalService journalService) {
    this.topicService = topicService;
    this.accService = accService;
    this.journalService = journalService;
  }

  public void update(Course course) throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> dao.update(course));
  }

  public void save(Course course) throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> dao.save(course));
  }

  public void delete(int id) throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    write(tm, () -> dao.delete(id));
  }

  public Optional<Course> findById(int id) throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> dao.find(id));
  }

  public List<CourseDTO> getBySelection(CourseSelection selection)
      throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    List<CourseDTO> dtoList = read(tm, () -> {
      List<Course> courses = dao.getAll();
      List<CourseDTO> list = new ArrayList<>();
      for (Course course : courses) list.add(getCourseDTO(tm, course));
      return list;
    });
    return selection.getSelected(dtoList);
  }

  public List<CourseDTO> getAvailableBySelection(int studentId,
                                                 CourseSelection selection)
      throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    List<CourseDTO> dtoList = read(tm, () -> {
      List<Course> courses = dao.getAvailableForStudent(studentId);
      List<CourseDTO> list = new ArrayList<>();
      for (Course course : courses) list.add(getCourseDTO(tm, course));
      return list;
    });
    return selection.getSelected(dtoList);
  }


  public List<CompletedCourseDTO> getCompletedCourses(int studentId)
      throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> {
      List<Course> courses = dao.getCompletedForStudent(studentId);
      List<CompletedCourseDTO> list = new ArrayList<>();
      for (final Course course : courses) {
        int grade = journalService
            .findByCourseAndStudent(tm, course.getId(), studentId)
            .get()
            .getGrade();
        list.add(new CompletedCourseDTO(getCourseDTO(tm, course), grade));
      }
      return list;
    });
  }

  public List<RegisteredCourseDTO> getRegisteredCourses(int studentId)
      throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> {
      List<Course> courses = dao.getRegisteredForStudent(studentId);
      List<RegisteredCourseDTO> list = new ArrayList<>();
      for (final Course course : courses) {
        Date date = journalService
            .findByCourseAndStudent(tm, course.getId(), studentId)
            .get()
            .getEnrollmentDate();
        list.add(new RegisteredCourseDTO(getCourseDTO(tm, course), date));
      }
      return list;
    });
  }

  public List<CourseDTO> getCoursesInProgress(int studentId)
      throws ServiceException {
    final CourseDAO dao = daoFactory.getCourseDAO();
    final TransactionManager tm = TransactionManager.getInstance();
    tm.initTransaction(dao);
    return read(tm, () -> {
      List<Course> courses = dao.getInProgressForStudent(studentId);
      List<CourseDTO> list = new ArrayList<>();
      for (Course course : courses) list.add(getCourseDTO(tm, course));
      return list;
    });
  }

  private CourseDTO getCourseDTO(TransactionManager tm, Course course)
      throws DAOException, MappingException {
    String topic = topicService.find(tm, course.getTopicId()).get().getName();
    String name = accService.find(tm, course.getTeacherId()).get().getFullName();
    int studentsCount = journalService.getStudentsCount(tm, course.getId());
    CourseDTOMapper mapper = new CourseDTOMapper(name, topic, studentsCount);
    return mapper.map(course);
  }

}