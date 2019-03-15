package com.example.wbdvassignment5javaserver.models;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
  private Integer id;
  private String title;
  private List<Topic> topics = new ArrayList<>();

  public Lesson() {
  }

  public Lesson(Integer id, String title) {
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

  public List<Topic> getTopics() {
    return topics;
  }

  public void setTopics(List<Topic> topics) {
    this.topics = topics;
  }
}
