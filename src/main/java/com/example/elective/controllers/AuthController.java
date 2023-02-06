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
    return "loginPage";
  }

  @PostMapping("/login")
  public String login(HttpServletRequest req, Model model) {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    accountService.findByCredentials(login, password).ifPresentOrElse(account -> {
      if (account.getBlock() != null) {
        model.addAttribute("accountBlocked", true);
        return;
      }
      HttpSession session = req.getSession();
      session.setAttribute(ACCOUNT_ATTR, account);
      String homeUrl = account.getRole().toString().toLowerCase();
      session.setAttribute(HOME_URL_ATTR, homeUrl);
    }, () -> model.addAttribute("loginFailed", true));
    return "redirect:../courses/all";
  }

  @GetMapping("/signup")
  public String signupForm(Model model) {
    model.addAttribute(LOGINS_ATTR, accountService.getLogins());
    model.addAttribute("student", new Account());
    return "signupForm";
  }

  @PostMapping("/signup")
  public String signup(@ModelAttribute("student") Account student) {
    studentService.save(student);
    return "redirect:login";
  }

  @PostMapping("/logout")
  public String logout(HttpServletRequest req) {
    HttpSession session = req.getSession();
    session.setAttribute(ACCOUNT_ATTR, null);
    session.setAttribute(HOME_URL_ATTR, LOGIN_URL);
    return "redirect:login";
  }

}
