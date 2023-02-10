package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Topic;
import com.example.elective.repository.AccountRepository;
import com.example.elective.repository.CourseRepository;

import com.example.elective.models.Course;
import com.example.elective.repository.TopicRepository;
import com.example.elective.services.interfaces.CourseService;
import com.example.elective.utils.CourseSelection;
import com.example.elective.utils.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
  public void persist(Course course, int teacherId, int topicId) {
    Account teacher = accountRepository.getReferenceById(teacherId);
    Topic topic = topicRepository.getReferenceById(topicId);
    course.setTeacher(teacher);
    course.setTopic(topic);
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
  public List<Course> getAll(CourseSelection selection) {
    Sort sort = getSort(selection.getSort());
    Topic topic = topicRepository.getByName(selection.getTopic());
    Account teacher = accountRepository.getByUsername(selection.getTeacher());
    return courseRepository.findAll(sort, teacher, topic);
  }

  @Override
  public List<Course> getAvailable(int studentId, CourseSelection selection) {
    Account student = accountRepository.getReferenceById(studentId);
    Sort sort = getSort(selection.getSort());
    Topic topic = topicRepository.getByName(selection.getTopic());
    Account teacher = accountRepository.getByUsername(selection.getTeacher());
    return courseRepository.getAvailable(sort, student, teacher, topic);
  }

  private Sort getSort(String sortStr) {
    return sortStr != null ?
        SortType.valueOf(sortStr).getSort() : Sort.unsorted();
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
  public List<Course> getOngoingCourses(int studentId) {
    Account student = accountRepository.getReferenceById(studentId);
    return courseRepository.getOngoing(student);
  }

}