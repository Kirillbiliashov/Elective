package com.example.elective.mappers.requestMappers;

import com.example.elective.models.Account;
import com.example.elective.models.Journal;
import com.example.elective.utils.Constants;
import com.example.elective.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.elective.utils.Constants.ACCOUNT_ATTR;

public class JournalRequestMapper implements RequestMapper<Journal> {

  @Override
  public Journal map(HttpServletRequest req) {
    int courseId = RequestUtils.getIdFromPathInfo(req.getPathInfo());
    HttpSession session = req.getSession();
    int studentId = ((Account) session.getAttribute(ACCOUNT_ATTR)).getId();
    return Journal.newBuilder()
        .setCourseId(courseId)
        .setStudentId(studentId)
        .setEnrollmentDate(Constants.CURRENT_DATE)
        .build();
  }

}
