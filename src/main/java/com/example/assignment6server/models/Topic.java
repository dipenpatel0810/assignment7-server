package com.example.assignment6server.models;

public class Topic {
  private Integer id;
  private String title;

  public Topic() {
  }

  public Topic(Integer id, String title) {
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
}
