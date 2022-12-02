package com.example.elective.services;

import com.example.elective.dao.RoleDAO;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Role;

import java.util.Optional;

public class RoleService extends AbstractService {

  private RoleDAO dao = new RoleDAO();

  public Optional<Role> getById(int id) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.find(id));
  }

  public Optional<Role> getByName(String name) throws ServiceException {
    transactionManager.initTransaction(dao);
    return performDaoReadOperation(() -> dao.findByName(name));
  }

}
