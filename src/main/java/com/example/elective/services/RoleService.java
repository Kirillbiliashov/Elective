package com.example.elective.services;

import com.example.elective.TransactionManager;
import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Role;

import java.util.Optional;

public class RoleService extends AbstractService {

  private RoleDAO dao = new RoleDAO();

  public Optional<Role> getById(int id) {
    transactionManager.initTransaction(dao);
    Optional<Role> optRole = dao.find(id);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return optRole;
  }

  public Optional<Role> getByName(String name) {
    transactionManager.initTransaction(dao);
    Optional<Role> optRole = dao.findByName(name);
    transactionManager.commitTransaction();
    transactionManager.endTransaction();
    return optRole;
  }

}
