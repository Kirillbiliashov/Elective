package com.example.elective.controllers;

import com.example.elective.config.SecurityConfig;
import com.example.elective.config.SpringConfig;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Role;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.JournalService;
import com.example.elective.services.interfaces.TeacherService;
import com.example.elective.utils.Pagination;
import com.example.elective.utils.PaginationUtils;
import com.example.elective.utils.SecurityUtils;
import com.example.elective.validator.AccountValidator;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.management.GarbageCollectorMXBean;

import static com.example.elective.utils.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, SecurityConfig.class})
public class TeacherControllerTest {

  private final int PAGE = 5;
  private final int SIZE = 5;
  private final int TEST_ID = 5;
  private final int TEST_GRADE = 70;
  private final int TEST_JOURNAL_ID = 32;

  @Mock
  private TeacherService teacherService;
  @Mock
  private JournalService journalService;
  @Mock
  private AccountService accountService;
  @Mock
  private SecurityUtils securityUtils;
  @Mock
  private AccountValidator accountValidator;
  @Mock
  private Page<Account> accountPage;
  @Mock
  private Page<Course> coursePage;
  @Mock
  private ModelMapper modelMapper;
  @Mock
  private Pagination pagination;
  @Autowired
  private PaginationUtils paginationUtils;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(new TeacherController(
        teacherService, journalService, accountService, securityUtils,
        accountValidator, modelMapper, paginationUtils)).build();
  }

  @Test
  public void testTeachersList() throws Exception {
    when(accountService.getAll(any(), any())).thenReturn(accountPage);
    mockMvc.perform(get("/teachers")
            .param(PAGE_PARAM, String.valueOf(PAGE))
            .param(SIZE_PARAM, String.valueOf(SIZE)))
        .andExpect(view().name(TEACHERS_PAGE))
        .andExpect(model().attributeExists(PAGE_PARAM))
        .andExpect(model().attributeExists(IS_FIRST_ATTR))
        .andExpect(model().attributeExists(IS_LAST_ATTR))
        .andExpect(model().attributeExists(SIZE_PARAM))
        .andExpect(model().attributeExists(TEACHERS_ATTR));
    verify(accountService, times(1)).getAll(Role.ROLE_TEACHER, pagination);
  }

  @Test
  public void testTeacherPage() throws Exception{
    when(securityUtils.getUserId()).thenReturn(TEST_ID);
    when(teacherService.findCourse(TEST_ID, PAGE)).thenReturn(coursePage);
    mockMvc.perform(get("/teachers/teacher")
        .param(PAGE_PARAM, String.valueOf(PAGE)))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(COURSES_ATTR))
        .andExpect(model().attributeExists(PAGE_PARAM))
        .andExpect(model().attributeExists(IS_FIRST_ATTR))
        .andExpect(model().attributeExists(IS_LAST_ATTR))
        .andExpect(model().attributeExists(CURR_DATE_ATTR))
        .andExpect(view().name(TEACHER_PAGE));
    verify(securityUtils, times(1)).getUserId();
    verify(teacherService, times(1)).findCourse(TEST_ID, PAGE);
  }

  @Test
  public void testTeacherRegistrationForm() throws Exception {
    mockMvc.perform(get("/teachers/register"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists(TEACHER_PARAM))
        .andExpect(view().name(TEACHER_REGISTRATION_PAGE));
  }

  @Test
  public void testRegisterTeacher() throws Exception {
    Account teacher = new Account();
    mockMvc.perform(post("/teachers/register")
            .flashAttr(TEACHER_PARAM, teacher))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("../"))
        .andExpect(model().attributeExists(TEACHER_PARAM));
    verify(teacherService, times(1)).save(teacher);
  }

  @Test
  public void testAddGrade() throws Exception {
    mockMvc.perform(post("/teachers/addGrade/{id}", TEST_JOURNAL_ID)
        .param(GRADE_PARAM, String.valueOf(TEST_GRADE)))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("../teacher"))
        .andExpect(model().size(0));
    verify(journalService, times(1)).updateGrade(TEST_JOURNAL_ID, TEST_GRADE);
  }

}
