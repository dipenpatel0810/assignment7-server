package com.example.assignment6server.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("list")
public class ListWidget extends Widget {
  private String listType;

  public String getListType() {
    return listType;
  }

  public void setListType(String listType) {
    this.listType = listType;
  }
}
