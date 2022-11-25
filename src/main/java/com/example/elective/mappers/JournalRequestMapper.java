package com.example.elective.mappers;

import com.example.elective.Utils;
import com.example.elective.models.Account;
import com.example.elective.models.Journal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JournalRequestMapper implements RequestMapper<Journal> {

  @Override
  public Journal map(HttpServletRequest req) {
    int courseId = Utils.getIdFromPathInfo(req.getPathInfo());
    HttpSession session = req.getSession();
    int studentId = ((Account) session.getAttribute("account")).getId();
    return new Journal()
        .setCourseId(courseId)
        .setStudentId(studentId)
        .setEnrollmentDate(Utils.CURRENT_DATE);
  }

}
