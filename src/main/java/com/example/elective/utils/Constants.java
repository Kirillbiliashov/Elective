package com.example.elective.utils;

import com.example.elective.selection.SortType;

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

  public static final String SORT_TYPES_ATTR = "sortTypes";

  public static final String LOGINS_ATTR = "logins";
  public static final String PAGE_ATTR = "page";
  public static final String NEXT_ATTR = "next";
  public static final String PREV_ATTR = "prev";
  public static final String DISPLAY_PARAM = "display";
  public static final String LANG_ATTR = "lang";
  public static final String COURSE_ATTR = "course";
  public static final String STUDENTS_ATTR = "students";
  public static final String JOURNALS_ATTR = "journals";
  public static final String CURR_DATE_ATTR = "currDate";
  public static final String AVAILABLE_COURSES_ATTR = "availableCourses";
  public static final String REGISTERED_COURSES_ATTR = "registeredCourses";
  public static final String COURSES_IN_PROGRESS_ATTR = "coursesInProgress";
  public static final String COMPLETED_COURSES_ATTR = "completedCourses";
  public static final String HOME_URL_ATTR = "homeUrl";
  public static final String ACCOUNT_ATTR = "account";
  public static final String PAGES_COUNT_ATTR = "pagesCount";
  public static final String TEACHER_ROLE = "Teacher";
  public static final String STUDENT_ROLE = "Student";
  public static final String COURSES_ATTR = "courses";
  public static final String ADMIN_URL = "/elective/admin";
  public static final String LOGIN_URL = "login";
  public static final String TOPICS_ATTR = "topics";
  public static final String TEACHERS_ATTR = "teachers";
  public static final String ACCOUNT_SERVICE = "accountService";
  public static final String COURSE_SERVICE = "courseService";
  public static final String JOURNAL_SERVICE = "journalService";
  public static final String STUDENT_SERVICE = "studentService";
  public static final String TEACHER_SERVICE = "teacherService";
  public static final String TOPIC_SERVICE = "topicService";
  public static final List<String> SORT_TYPES = Arrays.stream(SortType.values()).
      map(Object::toString).
      map(String::toLowerCase).
      collect(Collectors.toList());
  private static final String ZONE = "Europe/Paris";
  public static final Date CURRENT_DATE = Date.valueOf(LocalDate.now(ZoneId.of(ZONE)));


}
