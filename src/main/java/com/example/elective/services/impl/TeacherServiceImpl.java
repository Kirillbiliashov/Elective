package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Course;
import com.example.elective.dto.JournalDTO;
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
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    try {
      return dao.findByTeacherId(teacherId, pagination);
    } finally {
      session.getTransaction().commit();
    }
  }

  @Override
  public int getCoursesCount(int teacherId) {
    Session session = SQLDAOFactory.getSession();
    CourseDAO dao = daoFactory.getCourseDAO();
    dao.setSession(session);
    session.beginTransaction();
    try {
      return dao.getCount(teacherId);
    } finally {
      session.getTransaction().commit();
    }
  }

  @Override
  public List<JournalDTO> getJournalList(int courseId) {
    Session session = SQLDAOFactory.getSession();
    JournalDAO journalDao = daoFactory.getJournalDAO();
    journalDao.setSession(session);
    session.beginTransaction();
    try {
      return journalDao
          .getByCourseId(courseId)
          .stream()
          .map(journal -> new JournalDTO()
              .setId(journal.getId())
              .setStudent(journal.getStudent().getFullName())
              .setGrade(journal.getGrade()))
          .toList();
    } finally {
      session.getTransaction().commit();
    }
  }

}
