package com.example.elective.services;

import com.example.elective.TransactionManager;
import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CourseService {

  private CourseDAO dao = new CourseDAO();
  private AccountDAO accDao = new AccountDAO();
  private TransactionManager transactionManager = new TransactionManager();
 private static final int NAME_IDX = 0;
 private static final int START_DATE_IDX = 1;
 private static final int END_DATE_IDX = 2;
 private static final int TOPIC_ID_IDX = 3;
  private static final int TEACHER_ID_IDX = 4;

  public void updateById(int id, String... updValues) {
    transactionManager.initTransaction(dao);
    Optional<Course> optCourse = dao.find(id);
    if (optCourse.isPresent()) {
      Course course = optCourse.get();
      updateFields(course, updValues);
      dao.update(course);
    }
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
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

  public void save(Course course) {
    transactionManager.initTransaction(dao);
    dao.save(course);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
  }

  public void delete(int id) {
    transactionManager.initTransaction(dao);
    dao.delete(id);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
  }

  public List<Course> getAll() {
    transactionManager.initTransaction(dao);
    List<Course> courses = dao.findAll();
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return courses;
  }

  public Optional<Course> getById(int id) {
    transactionManager.initTransaction(dao);
    Optional<Course> optCourse = dao.find(id);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return optCourse;
  }


  public Map<Course, Account> getCourseTeacher(List<Course> courses) {
    transactionManager.initTransaction(accDao);
    Map<Course, Account> map = new LinkedHashMap<>();
    courses.forEach(course -> map.put(course,
        accDao.find(course.getTeacherId()).orElse(null)));
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return map;
  }

}
