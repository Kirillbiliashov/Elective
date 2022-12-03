package com.example.elective.services;

import com.example.elective.CourseSelection;
import static com.example.elective.CourseSelection.SortType;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService extends AbstractService {

  private CourseDAO dao = new CourseDAO();
  private AccountDAO accDao = new AccountDAO();
 private static final int NAME_IDX = 0;
 private static final int START_DATE_IDX = 1;
 private static final int END_DATE_IDX = 2;
 private static final int TOPIC_ID_IDX = 3;
  private static final int TEACHER_ID_IDX = 4;

  public void updateById(int id, String... updValues) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> {
      Optional<Course> optCourse = dao.find(id);
      if (optCourse.isPresent()) {
        Course course = optCourse.get();
        updateFields(course, updValues);
        dao.update(course);
      }
    });
  }

  private void updateFields(Course course, String... values) {
    String name = values[NAME_IDX];
    Date startDate = Date.valueOf(values[START_DATE_IDX]);
    Date endDate = Date.valueOf(values[END_DATE_IDX]);
    int topicId = Integer.parseInt(values[TOPIC_ID_IDX]);
    int teacherId = Integer.parseInt(values[TEACHER_ID_IDX]);
    course.getBuilder()
        .setName(name)
        .setStartDate(startDate)
        .setEndDate(endDate)
        .setTopicId(topicId)
        .setTeacherId(teacherId);
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
    List<Course> sortedCourses = performDaoReadOperation(() -> {
      SortType sort = selection.getSort();
      if (sort == SortType.STUDENTS || sort == SortType.STUDENTS_DESC) {
        return dao.getAllOrderedByStudentCount(sort == SortType.STUDENTS);
      }
      if (sort == SortType.NONE) return dao.findAll();
      return dao.getOrderedBy(sort.getOrderBy());
    });
    int topicId = selection.getTopicId();
    int teacherId = selection.getTeacherId();
    return sortedCourses
        .stream()
        .filter(c -> c.getTopicId() == topicId || topicId == 0)
        .filter(c -> c.getTeacherId() == teacherId || teacherId == 0)
        .collect(Collectors.toList());
  }

}
