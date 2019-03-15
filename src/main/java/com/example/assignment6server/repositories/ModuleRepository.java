package com.example.assignment6server.repositories;

import com.example.assignment6server.models.Course;
import com.example.assignment6server.models.Module;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModuleRepository extends CrudRepository<Module, Integer> {
  @Query("SELECT module FROM Module module WHERE module.course=:course ")
  public List<Module> findAllModules(@Param("course") Course course);
}
