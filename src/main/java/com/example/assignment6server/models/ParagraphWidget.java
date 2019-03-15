package com.example.assignment6server.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("paragraph")
public class ParagraphWidget extends Widget{

}
