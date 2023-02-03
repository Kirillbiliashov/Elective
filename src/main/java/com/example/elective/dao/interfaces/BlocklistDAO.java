package com.example.elective.dao.interfaces;

import com.example.elective.exceptions.DAOException;
import com.example.elective.models.Blocklist;

public interface BlocklistDAO extends DAO<Blocklist> {
  void save(int studentId);
}
