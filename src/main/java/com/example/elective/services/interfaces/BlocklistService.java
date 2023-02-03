package com.example.elective.services.interfaces;

import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Blocklist;

import java.util.Optional;

/**
 * Interface describing methods that student service should implement
 * @author Kirill Biliashov
 */

public interface BlocklistService {

  void changeBlockStatus(int id);
  Optional<Blocklist> getBlockStatus(int id);
}
