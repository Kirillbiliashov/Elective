package com.example.elective.utils;

import com.example.elective.security.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

  public int getUserId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    AccountDetails accountDetails = (AccountDetails) auth.getPrincipal();
    return accountDetails.account().getId();
  }

}
