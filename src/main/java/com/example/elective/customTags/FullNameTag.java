package com.example.elective.customTags;

import com.example.elective.models.Account;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FullNameTag extends SimpleTagSupport {

  private Account account;

  public void setTarget(Account account) {
    this.account = account;
  }

  @Override
  public void doTag() throws IOException {
    String fullName = account == null ? "" :
        account.getFirstName() + " " + account.getLastName();
     getJspContext().getOut().print(fullName);
  }

}
