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
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  @ManyToOne(targetEntity = Module.class)
  @JsonIgnore
  private Module module;
  @OneToMany(mappedBy = "lesson")
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

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }

  public List<Topic> getTopics() {
    return topics;
  }

  public void setTopics(List<Topic> topics) {
    this.topics = topics;
  }

  //our implementation which we use to update the Lesson
  public void set(Lesson newLesson) {
    this.title = newLesson.title;
  }
}
