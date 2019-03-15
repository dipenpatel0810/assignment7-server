package com.example.assignment6server.repositories;


import com.example.assignment6server.models.Lesson;
import com.example.assignment6server.models.Module;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson,Integer> {

  @Query("SELECT lesson FROM Lesson lesson WHERE lesson.module=:module")
  public List<Lesson> findAllLessons(@Param("module") Module module);

}
