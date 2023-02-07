package com.example.elective.services.interfaces;

import com.example.elective.dto.JournalDTO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that teacher service should implement
 * @author Kirill Biliashov
 */

public interface TeacherService {
  Optional<Course> findCourse(int teacherId, Pagination pagination);
  long getCoursesCount(int teacherId);
  void save(Account teacher);
  List<Journal> getStudents(int courseId);
}
