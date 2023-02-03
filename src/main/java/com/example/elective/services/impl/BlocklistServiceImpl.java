package com.example.elective.services.impl;

import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.dao.sql.SQLDAOFactory;
import com.example.elective.models.Blocklist;
import com.example.elective.services.AbstractService;
import com.example.elective.services.interfaces.BlocklistService;
import org.hibernate.Session;

import java.util.*;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

public class BlocklistServiceImpl extends AbstractService implements BlocklistService {

  @Override
  public void changeBlockStatus(int id) {
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    write(() -> {
      Optional<Blocklist> optBlocklist = dao.find(id);
      if (optBlocklist.isPresent()) dao.delete(id);
      else dao.save(id);
    }, dao);
  }

  @Override
  public Optional<Blocklist> getBlockStatus(int id) {
    BlocklistDAO dao = daoFactory.getBlocklistDAO();
    return read(() -> dao.find(id), dao);
  }

}
