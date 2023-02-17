package com.example.elective.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that stores application constants
 * @author Kirill Biliashov
 */
public class Constants {

  public static final String COURSE_ATTR = "course";
  public static final String STUDENTS_ATTR = "students";
  public static final String STUDENT_ATTR = "student";
  public static final String CURR_DATE_ATTR = "currDate";
  public static final String AVAILABLE_COURSES_ATTR = "availableCourses";
  public static final String REGISTERED_COURSES_ATTR = "registeredCourses";
  public static final String ONGOING_COURSES_ATTR = "coursesInProgress";
  public static final String COMPLETED_COURSES_ATTR = "completedCourses";
  public static final String COURSES_ATTR = "courses";
  public static final String TOPICS_ATTR = "topics";
  public static final String TEACHERS_ATTR = "teachers";
  private static final String ZONE = "Europe/Paris";
  public static final Date CURRENT_DATE = Date.valueOf(LocalDate.now(ZoneId.of(ZONE)));
  public static final String SIGNUP_URL = "/auth/signup";
  public static final String LOGIN_URL = "/auth/login";
  public static final String SORT_PARAM = "sort";
  public static final String TEACHER_PARAM = "teacher";
  public static final String TOPIC_PARAM = "topic";
  public static final String TOPIC_ID_PARAM = "topicId";
  public static final String TEACHER_ID_PARAM = "teacherId";
  public static final String PAGE_PARAM = "page";
  public static final String SIZE_PARAM = "size";
  public static final String IS_LAST_ATTR = "isLast";
  public static final String IS_FIRST_ATTR = "isFirst";
  public static final String GRADE_PARAM = "grade";
  public static final String LOGIN_PAGE = "auth/login";
  public static final String SIGNUP_PAGE = "auth/signup";
  public static final String COURSE_PAGE = "courses/course";
  public static final String ALL_COURSES_PAGE = "courses/all";
  public static final String ADD_COURSE_PAGE = "courses/add";
  public static final String AVAILABLE_COURSES_PAGE = "courses/available";
  public static final String REGISTERED_COURSES_PAGE = "courses/registered";
  public static final String ONGOING_COURSES_PAGE = "courses/ongoing";
  public static final String COMPLETED_COURSES_PAGE = "courses/completed";
  public static final String STUDENTS_PAGE = "students/all";
  public static final String TEACHERS_PAGE = "teachers/all";
  public static final String TEACHER_PAGE = "teachers/teacher";
  public static final String TEACHER_REGISTRATION_PAGE = "teachers/registration";
}
