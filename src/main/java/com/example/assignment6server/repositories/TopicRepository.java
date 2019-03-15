package com.example.assignment6server.repositories;

import com.example.assignment6server.models.Lesson;
import com.example.assignment6server.models.Topic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
  @Query("SELECT topic FROM Topic topic WHERE topic.lesson=:lesson")
  public List<Topic> findAllTopics(@Param("lesson") Lesson lesson);
}
