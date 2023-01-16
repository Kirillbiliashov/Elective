package com.example.elective.services.interfaces;

import com.example.elective.dto.JournalDTO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Course;
import com.example.elective.selection.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing methods that teacher service should implement
 * @author Kirill Biliashov
 */

public interface TeacherService {
  Optional<Course> findCourse(int teacherId, Pagination pagination) throws ServiceException;

  int getCoursesCount(int teacherId) throws ServiceException;

  List<JournalDTO> getJournalList(int courseId) throws ServiceException;
}
