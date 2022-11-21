package com.example.elective.servlets;

import com.example.elective.dao.AccountDAO;
import com.example.elective.dao.CourseDAO;
import com.example.elective.dao.JournalDAO;
import com.example.elective.models.Account;
import com.example.elective.models.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<Course> courses = CourseDAO.getAll();
    String sortType = req.getParameter("sort");
    if (sortType != null) sortCourses(sortType, courses);
    req.setAttribute("courses", getCourseAccountMap(courses));
    req.setAttribute("students", AccountDAO.getByRole("Student"));
    req.setAttribute("teachers", AccountDAO.getByRole("Teacher"));
    req.getRequestDispatcher("admin.jsp").forward(req, resp);
  }

  private void sortCourses(String sortType, List<Course> courses) {
    Comparator<Course> courseComparator = getCourseComparator(sortType);
    if (courseComparator != null) courses.sort(courseComparator);
  }

  private Comparator<Course> getCourseComparator(String sortType) {
    Comparator<Course> courseComparator = null;
    if (sortType.equals("name")) {
      courseComparator = getNameComparator();
    } else if (sortType.equals("name_reverse")) {
      courseComparator = getNameComparator().reversed();
    } else if (sortType.equals("duration_asc")) {
      courseComparator = getDurationComparator();
    } else if (sortType.equals("duration_desc")) {
      courseComparator = Collections.reverseOrder(getDurationComparator());
    } else if (sortType.equals("students_asc")) {
      courseComparator = getStudentComparator();
    } else if (sortType.equals("students_desc")) {
      courseComparator = Collections.reverseOrder(getStudentComparator());
    }
    return courseComparator;
  }

  private Comparator<Course> getNameComparator() {
    return Comparator.comparing(Course::getName);
  }

  private Comparator<Course> getDurationComparator() {
  return Comparator.comparing(c -> c.getEndDate().getTime() -
      c.getStartDate().getTime());
  }

  private Comparator<Course> getStudentComparator() {
    return Comparator.comparing(c -> JournalDAO.getByCourseId(c.getId()).size());
  }


  private Map<Course, Account> getCourseAccountMap(List<Course> courses) {
    Map<Course, Account> map = new LinkedHashMap<>();
    courses.forEach(course -> map.put(course,
        AccountDAO.getById(course.getTeacherId()).orElse(null)));
    return map;
  }

}
