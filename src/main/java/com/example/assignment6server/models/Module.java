package com.example.wbdvassignment5javaserver.models;

import java.util.ArrayList;
import java.util.List;

public class Module {
  private Integer id;
  private String title;
  private List<Lesson> lessons = new ArrayList<>();

  public Module() {
  }

  public Module(Integer id, String title) {
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

  public List<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
  }
}
