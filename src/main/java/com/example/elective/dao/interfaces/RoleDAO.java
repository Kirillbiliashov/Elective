package com.example.elective.dao.interfaces;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Role;

import java.util.Optional;

public interface RoleDAO extends DAO<Role> {
  Optional<Role> findByName(String name) throws DAOException;
}

