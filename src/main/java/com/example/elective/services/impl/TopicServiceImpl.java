package com.example.elective.services.impl;

import com.example.elective.models.Topic;
import com.example.elective.repository.TopicRepository;
import com.example.elective.services.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class containing business logic methods regarding topics
 * @author Kirill Biliashov
 */

@Service
@Transactional(readOnly = true)
public class TopicServiceImpl implements TopicService {

  private final TopicRepository topicRepository;

  @Autowired
  public TopicServiceImpl(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  @Override
  public List<Topic> getAll() {
    return topicRepository.findAll();
  }

  @Override
  public Topic get(int id) {
    return topicRepository.findById(id).orElse(null);
  }

}
