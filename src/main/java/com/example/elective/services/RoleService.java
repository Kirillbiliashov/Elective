package com.example.elective.services;

import com.example.elective.TransactionManager;
import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Role;

import java.util.Optional;

public class RoleService extends AbstractService {

  private RoleDAO dao = new RoleDAO();

  public Optional<Role> getById(int id) {
    transactionManager.initTransaction(dao);
    return performReadOperation(() -> dao.find(id));
  }

  public Optional<Role> getByName(String name) {
    transactionManager.initTransaction(dao);
    return performReadOperation(() -> dao.findByName(name));
  }

}
