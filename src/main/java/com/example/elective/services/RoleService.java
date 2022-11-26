package com.example.elective.services;

import com.example.elective.dao.RoleDAO;
import com.example.elective.models.Role;

import java.util.Optional;

public class RoleService {

  private RoleDAO dao = new RoleDAO();

  public Optional<Role> getById(int id) {
    return dao.findById(id);
  }

  public Optional<Role> getByName(String name) {
    return dao.findByName(name);
  }
}
