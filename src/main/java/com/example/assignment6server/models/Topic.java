package com.example.assignment6server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Topic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  @OneToMany(mappedBy = "topic")
  private List<Widget> widgets;
  @ManyToOne(targetEntity = Lesson.class)
  @JsonIgnore
  private Lesson lesson;

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

  public Lesson getLesson() {
    return lesson;
  }

  public void setLesson(Lesson lesson) {
    this.lesson = lesson;
  }

  public List<Widget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<Widget> widgets) {
    this.widgets = widgets;
  }

  //our implementation which we use to update the Topic
  public void set(Topic newTopic) {
    this.title = newTopic.title;
  }
}
