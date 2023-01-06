package com.example.elective.daos;

import com.example.elective.dao.sql.mysql.CourseMySqlDAO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Course;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CourseDAOTest {

  private static final CourseDAOTestClass dao = new CourseDAOTestClass();
  private static final String ERROR_MSG = "unable to save course";

  @Mock
  private Connection conn;

  @Mock
  private Statement stmt;

  @Mock
  private PreparedStatement ps;

  @Mock
  private ResultSet rs;

  @Mock
  private Mapper<ResultSet, Course> mapper;

  @Mock
  private Logger logger;

  @Mock
  private SQLException e;

  @BeforeEach
  void beforeEach() throws Exception {
    MockitoAnnotations.openMocks(this);
    dao.setConnection(conn);
    dao.setMapper(mapper);
    dao.setLogger(logger);
    when(conn.createStatement()).thenReturn(stmt);

  }

  @Test
  void testFindAll() throws Exception {
    final int EXP_COURSES_COUNT = 5;
    when(stmt.executeQuery(anyString())).thenReturn(rs);
    when(rs.next()).thenReturn(true, true, true, true, true, false);
    when(mapper.map(rs)).thenReturn(Course.newBuilder().build());
    Assertions.assertEquals(EXP_COURSES_COUNT, dao.getAll().size());
  }

  @Test
  void testSaveThrowsException() throws Exception {
    when(conn.prepareStatement(anyString(), anyInt())).thenReturn(ps);
    when(e.getMessage()).thenReturn(ERROR_MSG);
    Course course = Course.newBuilder()
        .setName("course1")
        .setStartDate(Date.valueOf("2022-12-12"))
        .setEndDate(Date.valueOf("2023-12-12"))
        .setTeacherId(5)
        .setTopicId(1)
        .build();
    doThrow(e).when(ps).executeUpdate();
    Assertions.assertThrows(DAOException.class, () -> dao.save(course));
    verify(ps, times(5)).setObject(anyInt(), any());
    verify(logger, times(1)).error(ERROR_MSG);
  }

  private static class CourseDAOTestClass extends CourseMySqlDAO {
    public void setMapper(Mapper<ResultSet, Course> mapper) {
      this.mapper = mapper;
    }

    public void setLogger(Logger logger) {
      this.logger = logger;
    }
  }

}
