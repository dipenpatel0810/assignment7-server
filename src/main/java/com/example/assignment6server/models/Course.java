package com.example.wbdvassignment5javaserver.models;

import java.util.ArrayList;
import java.util.List;

public class Course {
  private Integer id;
  private String title;
  private List<Module> modules = new ArrayList<>();
  private String author;

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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
