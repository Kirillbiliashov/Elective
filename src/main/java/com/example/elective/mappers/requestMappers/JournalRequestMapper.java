package com.example.elective.mappers.requestMappers;

import com.example.elective.models.Account;
import com.example.elective.models.Journal;
import com.example.elective.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;

/**
 * Class that maps HttpServletRequest to Journal
 * @author Kirill Biliashov
 */

public class JournalRequestMapper implements RequestMapper<Journal> {

  @Override
  public Journal map(HttpServletRequest req) {
    HttpSession session = req.getSession();
    int studentId = ((Account) session.getAttribute(ACCOUNT_ATTR)).getId();
    return Journal.newBuilder()
        .setStudentId(studentId)
        .setEnrollmentDate(Constants.CURRENT_DATE)
        .build();
  }

}
