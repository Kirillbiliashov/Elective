package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Topic;
import com.example.elective.repository.AccountRepository;
import com.example.elective.repository.CourseRepository;

import com.example.elective.models.Course;
import com.example.elective.repository.TopicRepository;
import com.example.elective.services.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Class containing business logic methods regarding courses
 *
 * @author Kirill Biliashov
 */

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private TopicRepository topicRepository;

  @Override
  @Transactional
  public void update(Course course, int teacherId, int topicId) {
    Account teacher = accountRepository.getReferenceById(teacherId);
    Topic topic = topicRepository.getReferenceById(topicId);
    course.setTeacher(teacher);
    course.setTopic(topic);
    courseRepository.save(course);
  }

  @Override
  @Transactional
  public void save(Course course, int topicId, int teacherId) {
    Topic topic = topicRepository.getReferenceById(topicId);
    Account teacher = accountRepository.getReferenceById(teacherId);
    course.setTopic(topic);
    course.setTeacher(teacher);
    courseRepository.save(course);
  }

  @Override
  @Transactional
  public void delete(int id) {
    courseRepository.deleteById(id);
  }

  @Override
  public Optional<Course> findById(int id) {
    return courseRepository.findById(id);
  }

  @Override
  public List<Course> getAll() {
    return courseRepository.findAll();
  }

  @Override
  public List<Course> getAvailable(int studentId) {
    Account student = accountRepository.getReferenceById(studentId);
    return courseRepository.getAvailable(student);
  }

  @Override
  public List<Course> getCompletedCourses(int studentId) {
    Account student = accountRepository.getReferenceById(studentId);
    return courseRepository.getCompleted(student);
  }

  @Override
  public List<Course> getRegisteredCourses(int studentId) {
    Account student = accountRepository.getReferenceById(studentId);
    return courseRepository.getRegistered(student);
  }

  @Override
  public List<Course> getCoursesInProgress(int studentId) {
    Account student = accountRepository.getReferenceById(studentId);
    return courseRepository.getOngoing(student);
  }

}