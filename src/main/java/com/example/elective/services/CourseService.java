package com.example.elective.services;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CourseService {

 private static final int NAME_IDX = 0;
 private static final int START_DATE_IDX = 1;
 private static final int END_DATE_IDX = 2;
 private static final int TOPIC_ID_IDX = 3;
  private static final int TEACHER_ID_IDX = 4;
  public void updateById(int id, String... updValues) {
    Optional<Course> optCourse = CourseDAO.getById(id);
    if (optCourse.isPresent()) {
      Course course = optCourse.get();
      updateFields(course, updValues);
      CourseDAO.update(course);
    }
  }

  private void updateFields(Course course, String... values) {
    String name = values[NAME_IDX];
    Date startDate = Date.valueOf(values[START_DATE_IDX]);
    Date endDate = Date.valueOf(values[END_DATE_IDX]);
    int topicId = Integer.parseInt(values[TOPIC_ID_IDX]);
    int teacherId = Integer.parseInt(values[TEACHER_ID_IDX]);
    course.setName(name)
        .setStartDate(startDate)
        .setEndDate(endDate)
        .setTopicId(topicId)
        .setTeacherId(teacherId);
  }



  public void save(Course course) {
    CourseDAO.save(course);
  }

  public void delete(int id) {
    CourseDAO.delete(id);
  }

  public List<Course> getAll() {
    return CourseDAO.getAll();
  }

  public Optional<Course> getById(int id) {
    return CourseDAO.getById(id);
  }


  public Map<Course, Account> getCourseTeacher(List<Course> courses) {
    Map<Course, Account> map = new LinkedHashMap<>();
    courses.forEach(course -> map.put(course,
        AccountDAO.getById(course.getTeacherId()).orElse(null)));
    return map;
  }

  public Map<Course, Journal> getCourseJournal(int studentId) {
    List<Course> courses = this.getAll();
    Map<Course, Journal> map = new LinkedHashMap<>();
    for (final Course course: courses) {
      map.put(course, JournalDAO.findByCourseAndStudent(course.getId(), studentId).orElse(null));
    }
    return map;
  }

}
