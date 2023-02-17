package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Role;
import com.example.elective.repository.AccountRepository;
import com.example.elective.repository.CourseRepository;
import com.example.elective.services.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class containing business logic methods regarding teachers
 * @author Kirill Biliashov
 */

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

  private final PasswordEncoder encoder;
  private final AccountRepository accountRepository;
  private final CourseRepository courseRepository;

  @Autowired
  public TeacherServiceImpl(PasswordEncoder encoder,
                            AccountRepository accountRepository,
                            CourseRepository courseRepository) {
    this.encoder = encoder;
    this.accountRepository = accountRepository;
    this.courseRepository = courseRepository;
  }

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
    teacher.setRole(Role.ROLE_TEACHER);
    teacher.setPassword(encoder.encode(teacher.getPassword()));
    accountRepository.save(teacher);
  }

}
