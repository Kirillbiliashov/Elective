package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.dto.JournalDTO;
import com.example.elective.models.Journal;
import com.example.elective.models.Role;
import com.example.elective.repository.AccountRepository;
import com.example.elective.repository.CourseRepository;
import com.example.elective.repository.JournalRepository;
import com.example.elective.selection.Pagination;
import com.example.elective.services.interfaces.TeacherService;
import com.example.elective.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Class containing business logic methods regarding teachers
 * @author Kirill Biliashov
 */

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private PasswordUtils passwordUtils;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private JournalRepository journalRepository;

  @Override
  public Optional<Course> findCourse(int teacherId, Pagination pagination) {
    Account teacher = accountRepository.getReferenceById(teacherId);
    Pageable pageable = PageRequest.of(pagination.getPage() - 1,
        pagination.getDisplayCount());
    return courseRepository.findByTeacher(teacher, pageable).stream().findFirst();
  }

  @Override
  public long getCoursesCount(int teacherId) {
    Account teacher = accountRepository.getReferenceById(teacherId);
    return courseRepository.countByTeacher(teacher);
  }

  @Override
  @Transactional
  public void save(Account teacher) {
    teacher.setRole(Role.TEACHER);
    teacher.setPassword(passwordUtils.hash(teacher.getPassword()));
    accountRepository.save(teacher);
  }

  @Override
  public List<Journal> getStudents(int courseId) {
    Course course = courseRepository.getReferenceById(courseId);
    return journalRepository.getByCourse(course);
  }

}
