package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Account;
import com.example.elective.models.Blocklist;
import com.example.elective.models.Role;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.StudentService;
import com.example.elective.utils.PasswordUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

@Service
public class StudentServiceImpl extends AbstractService implements StudentService {

  @Autowired
  private PasswordUtils passwordUtils;

  @Override
  public void changeBlockStatus(int id) {
    Session session = SQLDAOFactory.getSession();
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    dao.setSession(session);
    session.beginTransaction();
    dao.find(id).ifPresentOrElse((blocklist) -> dao.delete(blocklist.getId()),
        () -> {
          Blocklist blocklist = new Blocklist();
          Account student = session.byId(Account.class).load(id);
          blocklist.setStudent(student);
          dao.save(blocklist);
        });
    session.getTransaction().commit();
  }

  @Override
  public void save(Account student) {
    AccountDAO dao = daoFactory.getAccountDAO();
    student.setRole(Role.STUDENT);
    student.setPassword(passwordUtils.hash(student.getPassword()));
    write(() -> dao.save(student), dao);
  }

}
