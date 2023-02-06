package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.dto.JournalDTO;
import com.example.elective.models.Journal;
import com.example.elective.models.Role;
import com.example.elective.selection.Pagination;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.TeacherService;
import com.example.elective.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Class containing business logic methods regarding teachers
 * @author Kirill Biliashov
 */

@Service
public class TeacherServiceImpl extends AbstractService implements TeacherService {

  @Autowired
  private PasswordUtils passwordUtils;

  @Override
  public Optional<Course> findCourse(int teacherId, Pagination pagination) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.findByTeacherId(teacherId, pagination), dao);
  }

  @Override
  public long getCoursesCount(int teacherId) {
    CourseDAO dao = daoFactory.getCourseDAO();
    return read(() -> dao.getCount(teacherId), dao);
  }

  @Override
  public void save(Account teacher) {
    AccountDAO dao = daoFactory.getAccountDAO();
    teacher.setRole(Role.TEACHER);
    teacher.setPassword(passwordUtils.hash(teacher.getPassword()));
    write(() -> dao.save(teacher), dao);
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
