package com.example.assignment6server.repositories;

import com.example.assignment6server.models.Course;
import com.example.assignment6server.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Integer> {
  @Query("SELECT course FROM Course  course WHERE course.author=:author")
  public List<Course> findAllCourses(@Param("author") User author);
}
