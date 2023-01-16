package com.example.elective.services.interfaces;

import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.models.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
  List<Topic> getAll() throws ServiceException;
  Optional<Topic> find(TransactionManager tm, int id) throws DAOException;
}
