package com.example.elective.controllers;

import com.example.elective.config.SecurityConfig;
import com.example.elective.config.SpringConfig;
import com.example.elective.models.Account;
import com.example.elective.models.Role;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.elective.utils.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, SecurityConfig.class})
public class StudentControllerTest {

  private final int PAGE = 2;
  private final int SIZE = 10;
  private final int TEST_ID = 3;
  @Mock
  private StudentService studentService;
  @Mock
  private AccountService accountService;
  @Mock
  private ModelMapper modelMapper;
  @Mock
  private Page<Account> page;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders
        .standaloneSetup(new StudentController(accountService,
            studentService, modelMapper))
        .build();
  }

  @Test
  public void testStudentsList() throws Exception {
    when(accountService.getAll(any(), any(), any())).thenReturn(page);
    mockMvc.perform(get("/students")
            .param("page", String.valueOf(PAGE))
            .param("size", String.valueOf(SIZE)))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("page"))
        .andExpect(model().attributeExists("pages"))
        .andExpect(model().attributeExists(STUDENTS_ATTR))
        .andExpect(view().name("students/all"));
    verify(accountService, times(1)).getAll(Role.ROLE_STUDENT, PAGE, SIZE);
  }

  @Test
  public void testChangeBlock() throws Exception {
    mockMvc.perform(post("/students/changeBlock/{id}", TEST_ID))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("../"));
    verify(studentService, times(1)).changeBlockStatus(TEST_ID);
  }

}
