package com.example.assignment6server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  @ManyToOne(targetEntity = User.class)
  @JsonIgnore
  private User author;
  @OneToMany(mappedBy = "course")
  private List<Module> modules = new ArrayList<>();

  public Course() {
  }

  public Course(Integer id, String title) {
    super();
    this.id = id;
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Module> getModules() {
    return modules;
  }

  public void setModules(List<Module> modules) {
    this.modules = modules;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  //our implementation which we use to update the course
  public void set(Course newCourse) {
    this.title = newCourse.title;
  }
}
