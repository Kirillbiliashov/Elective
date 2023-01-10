package com.example.elective.customTags;

import com.example.elective.models.Account;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Class that represents custom tag library
 * @author Kirill Biliashov
 */

public class InfoTag extends SimpleTagSupport {

  private Account account;

  public void setTarget(Account account) {
    this.account = account;
  }

  @Override
  public void doTag() throws IOException {
    getJspContext().getOut().println(account.getFullName() + "(" + account.getUsername() + ")");
  }

}
