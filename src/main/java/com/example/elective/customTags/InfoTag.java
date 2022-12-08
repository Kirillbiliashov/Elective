package com.example.elective.customTags;

import com.example.elective.models.Account;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class InfoTag extends SimpleTagSupport {

  private Account account;

  public void setTarget(Account account) {
    this.account = account;
  }

  @Override
  public void doTag() throws JspException, IOException {
    getJspContext().getOut().println(account.getFirstName() + " " +
        account.getLastName() + "(" + account.getLogin() + ")");
  }

}
