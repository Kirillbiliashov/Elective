package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.repository.AccountRepository;
import com.example.elective.repository.CourseRepository;
import com.example.elective.repository.JournalRepository;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class containing business logic methods regarding journal
 * @author Kirill Biliashov
 */

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

  @Autowired
  private JournalRepository journalRepository;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public void save(int courseId, int studentId) {
    Journal journal = new Journal().setEnrollmentDate(Constants.CURRENT_DATE);
    Course course = courseRepository.getReferenceById(courseId);
    Account student = accountRepository.getReferenceById(studentId);
    journal.setCourse(course);
    journal.setStudent(student);
    journalRepository.save(journal);
  }

  @Override
  public void updateGrade(int id, int grade) {
    journalRepository.findById(id).ifPresent(journal -> journal.setGrade(grade));
  }

}
