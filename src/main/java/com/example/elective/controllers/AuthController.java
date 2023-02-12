package com.example.elective.controllers;

import com.example.elective.models.Account;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private StudentService studentService;

  @GetMapping("/login")
  public String loginPage() {
    return "auth/login";
  }

  @GetMapping("/signup")
  public String signupForm(@ModelAttribute("student") Account student) {
    return "auth/signup";
  }

  @PostMapping("/signup")
  public String signup(@ModelAttribute("student") Account student) {
    studentService.save(student);
    return "redirect:../login";
  }

}
