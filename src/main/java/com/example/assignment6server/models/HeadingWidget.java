package com.example.assignment6server.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("heading")
public class HeadingWidget extends Widget {
  private Integer size;

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
}

