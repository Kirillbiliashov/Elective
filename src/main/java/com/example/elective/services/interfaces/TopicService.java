package com.example.elective.services.interfaces;

import com.example.elective.models.Topic;

import java.util.List;

/**
 * Interface describing methods that topic service should implement
 * @author Kirill Biliashov
 */

public interface TopicService {
  List<Topic> getAll();
  Topic get(int id);
}
