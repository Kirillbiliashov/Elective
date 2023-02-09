package com.example.elective.services.interfaces;

import com.example.elective.dto.JournalDTO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that teacher service should implement
 * @author Kirill Biliashov
 */

public interface TeacherService {
  Page<Course> findCourse(int teacherId, Integer page);
  void save(Account teacher);
}
