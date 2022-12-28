package com.example.elective.services;

import com.example.elective.CourseSelection;
import static com.example.elective.CourseSelection.SortType;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import dto.CourseDTO;

import java.util.*;
import java.util.stream.Collectors;

public class CourseService extends AbstractService {

  private CourseDAO dao = daoFactory.getCourseDAO();
  private AccountDAO accDao = daoFactory.getAccountDAO();
  private TopicDAO topicDAO = daoFactory.getTopicDAO();

  public void update(Course course) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.update(course));
  }

  public void save(Course course) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.save(course));
  }

  public void delete(int id) throws ServiceException {
    transactionManager.initTransaction(dao);
    performDaoWriteOperation(() -> dao.delete(id));
  }

  public Optional<Course> getById(int id) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.find(id));
  }

  public Map<Course, Account> getCourseTeacher(List<Course> courses) throws ServiceException {
    transactionManager.initTransaction(accDao);
    return performDaoReadOperation(() -> {
      Map<Course, Account> map = new LinkedHashMap<>();
      for (final Course course : courses) {
        map.put(course,
            accDao.find(course.getTeacherId()).orElse(null));
      }
      return map;
    });
  }

  public List<CourseDTO> getBySelection(CourseSelection selection) throws ServiceException {
    transactionManager.initTransaction(accDao, dao, topicDAO);
    String topic = selection.getTopic();
    String teacher = selection.getTeacher();
    List<CourseDTO> dtoList = getCourseDTOList(selection.getSort());
    System.out.println("topic: " + topic);
    System.out.println("teacher: " + teacher);
    return dtoList
        .stream()
        .filter(c -> c.getTopic().equals(topic) || topic.equals("Any"))
        .filter(c -> c.getTeacher().equals(teacher) || teacher.equals("Any"))
        .collect(Collectors.toList());
  }

    private List<CourseDTO> getCourseDTOList(SortType sort) throws ServiceException {
    return performDaoReadOperation(() -> {
      List<Course> courses = getSortedCourses(sort);
      List<CourseDTO> courseDtoList = new ArrayList<>();
      for (Course course: courses) {
        String topicName = topicDAO.find(course.getTopicId()).get().getName();
        Account teacher = accDao.find(course.getTeacherId()).get();
        String teacherName = teacher.getFirstName() + " " + teacher.getLastName();
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setStartDate(course.getStartDate());
        dto.setEndDate(course.getEndDate());
        dto.setTeacher(teacherName);
        dto.setTopic(topicName);
        courseDtoList.add(dto);
      }
      return courseDtoList;
    });
  }

  private List<Course> getSortedCourses(SortType sort) throws DAOException {
    if (sort == SortType.STUDENTS || sort == SortType.STUDENTS_DESC) {
      return dao.getAllOrderedByStudentCount(sort == SortType.STUDENTS);
    }
    if (sort == SortType.NONE) return dao.findAll();
    return dao.getOrderedBy(sort.getOrderBy());
  }

}