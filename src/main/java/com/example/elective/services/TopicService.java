package com.example.elective.services;

import com.example.elective.dao.TopicDAO;
import com.example.elective.models.Topic;

import java.util.List;

public class TopicService {

  public List<Topic> getAll() {
    return TopicDAO.getAll();
  }

}
