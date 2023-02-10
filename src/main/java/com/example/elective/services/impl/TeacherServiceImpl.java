package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.dto.JournalDTO;
import com.example.elective.models.Journal;
import com.example.elective.models.Role;
import com.example.elective.repository.AccountRepository;
import com.example.elective.repository.CourseRepository;
import com.example.elective.repository.JournalRepository;
import com.example.elective.services.interfaces.TeacherService;
import com.example.elective.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @Override
  public Page<Course> findCourse(int teacherId, Integer page) {
    Account teacher = accountRepository.getReferenceById(teacherId);
    Pageable pageable = page != null ?
        PageRequest.of(page, 1) : Pageable.unpaged();
    return courseRepository.findByTeacher(teacher, pageable);
  }

  @Override
  @Transactional
  public void save(Account teacher) {
    teacher.setRole(Role.TEACHER);
    teacher.setPassword(passwordUtils.hash(teacher.getPassword()));
    accountRepository.save(teacher);
  }

}
