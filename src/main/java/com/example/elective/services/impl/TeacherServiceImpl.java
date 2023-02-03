package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Course;
import com.example.elective.dto.JournalDTO;
import com.example.elective.models.Journal;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.TeacherService;
import org.hibernate.Session;

import java.util.*;

/**
 * Class containing business logic methods regarding teachers
 * @author Kirill Biliashov
 */

public class TeacherServiceImpl extends AbstractService implements TeacherService {

  @Override
  public Optional<Course> findCourse(int teacherId, Pagination pagination) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.findByTeacherId(teacherId, pagination), dao);
  }

  @Override
  public int getCoursesCount(int teacherId) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.getCount(teacherId), dao);
  }

  @Override
  public List<JournalDTO> getJournalList(int courseId) {
    JournalDAO dao = daoFactory.getJournalDAO();
    List<Journal> studentCourseList = read(() -> dao.getByCourseId(courseId), dao);
    return studentCourseList.stream()
        .map(journal -> new JournalDTO()
            .setId(journal.getId())
            .setStudent(journal.getStudent().getFullName())
            .setGrade(journal.getGrade()))
        .toList();
  }

}
