package com.example.elective.services;

import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Role;

import java.util.Optional;

public class RoleService {

  public Optional<Role> getById(int id) {
    return RoleDAO.findById(id);
  }

  public Optional<Role> getByName(String name) {
    return RoleDAO.findByName(name);
  }
}
