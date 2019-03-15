package com.example.assignment6server.services;

import com.example.assignment6server.models.User;
import com.example.assignment6server.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class UserService {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @PostMapping("/api/register")
  public User register(@RequestBody User newUser, HttpSession session) {
    boolean uniqueUser = true;
    List<User> listOfUsers = (List<User>) userRepository.findAll();
    for (User u : listOfUsers) {
      if (u.getUsername().equalsIgnoreCase(newUser.getUsername())) {
        uniqueUser = false;
      }
    }

    if (uniqueUser) {
      userRepository.save(newUser);
      session.setAttribute("currentUser", newUser);
      return newUser;
    } else {
      return new User();
    }
  }

  //  login
  @PostMapping("/api/login")
  public User login(@RequestBody User credentials, HttpSession session) {
    List<User> users = (List<User>) userRepository.findAll();
    for (User user : users) {
      if (user.getUsername().equals(credentials.getUsername()) &&
          user.getPassword().equals(credentials.getPassword())) {
        session.setAttribute("currentUser", user);
        return user;
      }
    }
    return new User();
  }

  //  profile
  @GetMapping("/api/profile")
  public User profile(HttpSession session) {
    User currentUser = (User)
        session.getAttribute("currentUser");
    return currentUser;
  }

  //  logout
  @PostMapping("/api/logout")
  public void logout(HttpSession session) {
    User currentUser = (User)
        session.getAttribute("currentUser");
    session.invalidate();
  }

  @GetMapping("/api/users/{uid}")
  public User findUserById(@PathVariable("uid") Integer uid) {
    return userRepository.findById(uid).get();
  }

  @PutMapping("/api/users/{uid}")
  public User updateUser(@PathVariable("uid") Integer uid, @RequestBody User newUser){
    System.out.println(newUser.getEmail());
    User user = userRepository.findById(uid).get();
    user.set(newUser);
    userRepository.save(user);
    System.out.println(user.getEmail());
    return user;
  }
}
