package com.example.elective.controllers;

import com.example.elective.models.Account;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.StudentService;
import com.example.elective.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.example.elective.utils.Constants.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final StudentService studentService;
  private final AccountValidator accountValidator;

  @Autowired
  public AuthController(StudentService studentService,
                        AccountValidator accountValidator) {
    this.studentService = studentService;
    this.accountValidator = accountValidator;
  }

  @GetMapping("/login")
  public String loginPage() {
    return "auth/login";
  }

  @GetMapping("/signup")
  public String signupForm(@ModelAttribute("student") Account student) {
    return "auth/signup";
  }

  @PostMapping("/signup")
  public String signup(@ModelAttribute("student") @Valid Account student,
                       BindingResult result) {
    accountValidator.validate(student, result);
    if (result.hasErrors()) return "auth/signup";
    studentService.save(student);
    return "redirect:../login";
  }

}
