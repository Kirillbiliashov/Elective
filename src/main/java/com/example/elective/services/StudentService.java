package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.CourseDAO;
import com.example.elective.dao.interfaces.JournalDAO;
import com.example.elective.dao.interfaces.TopicDAO;
import com.example.elective.dto.CourseDTO;
import com.example.elective.dto.RegisteredCourseDTO;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.MappingException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.dtoMappers.CompletedCourseDTOMapper;
import com.example.elective.mappers.dtoMappers.CourseDTOMapper;
import com.example.elective.mappers.dtoMappers.RegisteredCourseDTOMapper;
import com.example.elective.models.Account;
import com.example.elective.models.Course;
import com.example.elective.models.Journal;
import com.example.elective.dto.CompletedCourseDTO;

import java.sql.Date;
import java.util.*;

public class StudentService extends AbstractService {

  private AccountDAO accDao = daoFactory.getAccountDAO();

  public void changeBlockStatus(int id) throws ServiceException {
    transactionManager.initTransaction(accDao);
    performDaoWriteOperation(() -> {
      Optional<Account> optAcc = accDao.find(id);
      if (optAcc.isPresent()) {
        Account acc = optAcc.get();
        acc.getBuilder().setBlocked(!acc.isBlocked());
        accDao.update(acc);
      }
    });
  }

  public List<Account> getAll() throws ServiceException {
    transactionManager.initTransaction(accDao);
    return performDaoReadOperation(() -> accDao.findByRole("Student"));
  }

}
