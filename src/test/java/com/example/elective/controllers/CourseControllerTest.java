package com.example.elective.controllers;

import com.example.elective.config.SecurityConfig;
import com.example.elective.config.SpringConfig;
import com.example.elective.models.Course;
import com.example.elective.models.Role;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.CourseService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TopicService;
import com.example.elective.utils.CourseSelection;
import com.example.elective.utils.SecurityUtils;
import com.example.elective.validator.CourseValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.misusing.CannotVerifyStubOnlyMock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static com.example.elective.utils.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, SecurityConfig.class})
public class CourseControllerTest {

  private final int TEST_COURSE_ID = 11;
  private final int TEST_STUDENT_ID = 2;
  private final int TEST_TOPIC_ID = 20;
  private final int TEST_TEACHER_ID = 10;

  private MockMvc mockMvc;
  @Mock
  private CourseService courseService;
  @Mock
  private AccountService accountService;
  @Mock
  private TopicService topicService;
  @Mock
  private CourseValidator courseValidator;
  @Mock
  private SecurityUtils securityUtils;
  @Mock
  private JournalService journalService;
  @Mock
  private ModelMapper modelMapper;
  private Course course;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(new CourseController(
        courseService, accountService, topicService, courseValidator,
            securityUtils, journalService, modelMapper)).build();
    when(securityUtils.getUserId()).thenReturn(TEST_STUDENT_ID);
  }

  @Test
  public void testGetById() throws Exception {
    course = new Course().setId(TEST_COURSE_ID);
    when(courseService.findById(TEST_COURSE_ID)).thenReturn(Optional.of(course));
    mockMvc.perform(get("/courses/{id}", TEST_COURSE_ID))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(COURSE_ATTR))
        .andExpect(model().attributeExists(TOPICS_ATTR))
        .andExpect(model().attributeExists(TEACHERS_ATTR))
        .andExpect(view().name(COURSE_PAGE));
    verifyTopicAndTeacherServiceCall();
  }

  @Test
  public void testGetAll() throws Exception {
    mockMvc.perform(get("/courses/all"))
        .andExpect(status().isOk())
        .andExpect(view().name(ALL_COURSES_PAGE))
        .andExpect(model().attributeExists(COURSES_ATTR))
        .andExpect(model().attributeExists(TEACHERS_ATTR))
        .andExpect(model().attributeExists(TOPICS_ATTR));
    verifyTopicAndTeacherServiceCall();
    verify(courseService, Mockito.times(1)).getAll(any(CourseSelection.class));
  }

  @Test
  public void testAdd() throws Exception {
    course = getPostCourse();
    mockMvc.perform(getAddCoursePostRequest(course))
        .andExpect(status().is3xxRedirection())
        .andExpect(model().attributeExists(COURSE_ATTR))
        .andExpect(model().attributeDoesNotExist(TOPICS_ATTR))
        .andExpect(model().attributeDoesNotExist(TEACHERS_ATTR))
        .andExpect(redirectedUrl("all"));
    verify(courseService, times(1)).persist(course, TEST_TEACHER_ID, TEST_TOPIC_ID);
  }

  @Test
  public void testAddCourseWithError() throws Exception {
    course = getPostCourse();
    doAnswer(invocation -> {
      BindingResult res = invocation.getArgument(1, BindingResult.class);
      res.rejectValue("name", "");
      return null;
    }).when(courseValidator).validate(any(), any());
    mockMvc.perform(getAddCoursePostRequest(course))
        .andExpect(view().name(ADD_COURSE_PAGE))
        .andExpect(model().attributeExists(TOPICS_ATTR))
        .andExpect(model().attributeExists(TEACHERS_ATTR));
    verifyTopicAndTeacherServiceCall();
  }

  private MockHttpServletRequestBuilder getAddCoursePostRequest(Course course) {
    return post("/courses/add")
        .flashAttr(COURSE_ATTR, course)
        .param(TOPIC_ID_PARAM, String.valueOf(TEST_TOPIC_ID))
        .param(TEACHER_ID_PARAM, String.valueOf(TEST_TEACHER_ID));
  }

  private Course getPostCourse() {
    LocalDate startDate = LocalDate.of(2023, Month.FEBRUARY, 20);
    LocalDate endDate = LocalDate.of(2023, Month.FEBRUARY, 27);
    return new Course()
        .setName("course name")
        .setDescription("course description")
        .setStartDate(Date.valueOf(startDate))
        .setEndDate(Date.valueOf(endDate));
  }

  @Test
  public void testEnroll() throws Exception {
    mockMvc.perform(post("/courses/enroll/{id}", TEST_COURSE_ID))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("../available"));
    verify(journalService, times(1)).save(TEST_COURSE_ID, TEST_STUDENT_ID);
  }

  @Test
  public void testAddCourseForm() throws Exception {
    mockMvc.perform(get("/courses/add"))
        .andExpect(status().isOk())
        .andExpect(view().name(ADD_COURSE_PAGE))
        .andExpect(model().attributeExists(TOPICS_ATTR))
        .andExpect(model().attributeExists(TEACHERS_ATTR))
        .andExpect(model().attributeExists(COURSE_ATTR))
        .andExpect(model().attributeExists(TOPIC_PARAM))
        .andExpect(model().attributeExists(TEACHER_PARAM));
    verifyTopicAndTeacherServiceCall();
  }

  @Test
  public void testGetAvailableCourses() throws Exception {
    String sort = "NAME";
    String teacher = "teacher";
    String topic = "topic";
    CourseSelection selection = new CourseSelection(sort, teacher, topic);
    mockMvc.perform(get("/courses/available").
            param(SORT_PARAM, sort)
            .param(TEACHER_PARAM, teacher)
            .param(TOPIC_PARAM, topic))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(TEACHERS_ATTR))
        .andExpect(model().attributeExists(TOPICS_ATTR))
        .andExpect(model().attributeExists(AVAILABLE_COURSES_ATTR))
        .andExpect(view().name(AVAILABLE_COURSES_PAGE));
    verifyTopicAndTeacherServiceCall();
    verify(courseService, times(1)).getAvailable(TEST_STUDENT_ID, selection);
  }

  @Test
  public void testRegisteredCourses() throws Exception {
    performGetCoursesRequest("/courses/registered", REGISTERED_COURSES_ATTR,
        REGISTERED_COURSES_PAGE);
    verify(courseService, times(1)).getRegisteredCourses(TEST_STUDENT_ID);
  }

  @Test
  public void testGetOngoingCourses() throws Exception {
    performGetCoursesRequest("/courses/ongoing", ONGOING_COURSES_ATTR,
        ONGOING_COURSES_PAGE);
    verify(courseService, times(1)).getOngoingCourses(TEST_STUDENT_ID);
  }

  @Test
  public void testGetCompletedCourses() throws Exception {
    performGetCoursesRequest("/courses/completed", COMPLETED_COURSES_ATTR,
        COMPLETED_COURSES_PAGE);
    verify(courseService, times(1)).getCompletedCourses(TEST_STUDENT_ID);
  }

  private void performGetCoursesRequest(String uri, String coursesAttrName,
                                        String view) throws Exception {
    mockMvc.perform(get(uri))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(coursesAttrName))
        .andExpect(view().name(view));
  }

  private void verifyTopicAndTeacherServiceCall() {
    verify(topicService, times(1)).getAll();
    verify(accountService, times(1)).getAll(Role.ROLE_TEACHER);
  }

}
