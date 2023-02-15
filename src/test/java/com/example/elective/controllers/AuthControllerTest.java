package com.example.elective.controllers;

import com.example.elective.config.SecurityConfig;
import com.example.elective.config.SpringConfig;
import com.example.elective.models.Account;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.StudentService;
import com.example.elective.validator.AccountValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, SecurityConfig.class})
public class AuthControllerTest {

  @Mock
  private StudentService studentService;
  @Mock
  private AccountValidator accountValidator;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders
        .standaloneSetup(new AuthController(studentService, accountValidator))
        .build();
  }

  @Test
  public void testLoginPage() throws Exception {
    mockMvc.perform(get("/auth/login"))
        .andExpect(status().isOk())
        .andExpect(model().size(0))
        .andExpect(view().name("auth/login"));
  }

  @Test
  public void testSignupForm() throws Exception {
    mockMvc.perform(get("/auth/signup"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("student"))
        .andExpect(view().name("auth/signup"));
  }

  @Test
  public void testSignup() throws Exception {
    Account student = new Account();
    mockMvc.perform(post("/auth/signup").flashAttr("student", student))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("../login"))
        .andExpect(model().attributeExists("student"));
    verify(studentService, times(1)).save(student);
  }

}
