package com.example.assignment6server.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;
  private String firstName;
  private String lastname;
  private String email;
  private String phone;
  private String dob;
  @OneToMany(mappedBy = "author")
  private List<Course> authoredCourses;

  public User() {
  }

  public User(Integer id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public List<Course> getAuthoredCourses() {
    return authoredCourses;
  }

  public void setAuthoredCourses(List<Course> authoredCourses) {
    this.authoredCourses = authoredCourses;
  }

  public void set(User newUser){
    this.username = newUser.username;
    this.email = newUser.email;
    this.phone = newUser.phone;
    this.dob = newUser.dob;
  }
}
