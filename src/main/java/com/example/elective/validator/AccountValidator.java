package com.example.elective.validator;

import com.example.elective.models.Account;
import com.example.elective.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

  @Autowired
  private AccountService accountService;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(Account.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Account account = (Account) target;
    if (accountService.findByUsername(account.getUsername()).isPresent()) {
      errors.rejectValue("username", "", "Username is already taken");
      return;
    }
    if (accountService.findByEmail(account.getEmail()).isPresent()) {
      errors.rejectValue("email", "", "Email is already taken");
    }
  }
}
