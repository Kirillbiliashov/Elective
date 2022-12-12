package com.example.elective.services;

import com.example.elective.CourseSelection;
import static com.example.elective.CourseSelection.SortType;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService extends AbstractService {

  private CourseDAO dao = daoFactory.getCourseDAO();
  private AccountDAO accDao = daoFactory.getAccountDAO();

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

  public List<Course> getAll() throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.findAll());
  }

  public Optional<Course> getById(int id) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.find(id));
  }

  public Map<Course, Account> getCourseTeacher(List<Course> courses) throws ServiceException {
    transactionManager.initTransaction(accDao);
    return performDaoReadOperation(() -> {
      Map<Course, Account> map = new LinkedHashMap<>();
      for (final Course course : courses) {
        map.put(course,
            accDao.find(course.getTeacherId()).orElse(null));
      }
      return map;
    });
  }

  public List<Course> getBySelection(CourseSelection selection) throws ServiceException {
    transactionManager.initTransaction(dao);
    List<Course> sortedCourses = getSortedCourses(selection.getSort());
    int topicId = selection.getTopicId();
    int teacherId = selection.getTeacherId();
    return sortedCourses
        .stream()
        .filter(c -> c.getTopicId() == topicId || topicId == 0)
        .filter(c -> c.getTeacherId() == teacherId || teacherId == 0)
        .collect(Collectors.toList());
  }

  private List<Course> getSortedCourses(SortType sort) throws ServiceException {
    return performDaoReadOperation(() -> {
      if (sort == SortType.STUDENTS || sort == SortType.STUDENTS_DESC) {
        return dao.getAllOrderedByStudentCount(sort == SortType.STUDENTS);
      }
      if (sort == SortType.NONE) return dao.findAll();
      return dao.getOrderedBy(sort.getOrderBy());
    });
  }

}