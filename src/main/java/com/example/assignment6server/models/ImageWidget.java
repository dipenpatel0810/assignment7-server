package com.example.assignment6server.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("image")
public class ImageWidget extends Widget{

}
