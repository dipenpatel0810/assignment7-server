package com.example.wbdvassignment5javaserver.services;

import com.example.wbdvassignment5javaserver.models.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class UserService {

  User dipen = new User(400, "dipen", "123");

  User smit = new User(401, "smit", "123");
  User param = new User(402, "param", "123");

  static List<User> users = new ArrayList<>();

  {
    dipen.setPhone("4702909905");
    dipen.setFirstName("Dipen");
    dipen.setLastname("Patel");
    dipen.setEmail("dipenpatel0810@gmail.com");
    dipen.setDob("1995-10-08");
    smit.setPhone("9979884890");
    smit.setFirstName("Smit");
    smit.setLastname("Shah");
    smit.setEmail("shahsmit48@gmail.com");
    smit.setDob("1996-05-12");
    param.setPhone("4702909909");
    param.setFirstName("Param");
    param.setLastname("Sheth");
    param.setEmail("sheth.par@gmail.com");
    param.setDob("1996-08-14");
    users.add(dipen);
    users.add(smit);
    users.add(param);
  }

  public static List<User> getUsers() {
    return users;
  }

  public static void setUsers(List<User> users) {
    UserService.users = users;
  }

  //Retrieves all users
  @GetMapping("/api/users")
  public List<User> findAllUsers() {
    return users;
  }

  //Retrieves a user whose id is uid
  @GetMapping("/api/users/{uid}")
  public User findUserById(@PathVariable("uid") Integer uid) {
    for (User user : users) {
      if (user.getId().equals(uid)) {
        return user;
      }
    }
    return new User();
  }

  //Registers a user. Username must be unique.
  @PostMapping(path = "/api/register", consumes = "application/json", produces = "application/json")
  public User register(@RequestBody User newUser, HttpSession session) {
    Date uniqueId = new Date();
    int id = (int) (uniqueId.getTime() / 1000);
    newUser.setId(id);
    boolean uniqueUser = true;
    session.setAttribute("currentUser", newUser);
    for (User user : users) {
      if (user.getUsername().equals(newUser.getUsername())) {
        uniqueUser = false;
      }
    }
    if (uniqueUser) {
      users.add(newUser);
      return newUser;
    } else {
      return new User();
    }
  }

  //Returns the currently logged in user
  @GetMapping("/api/profile")
  public User profile(HttpSession session) {
    User currentUser = (User)
        session.getAttribute("currentUser");
    return currentUser;
  }

  //if credentials.username and credentials.password matches in out records,
  // then make this user current user
  @PostMapping(path = "/api/login", consumes = "application/json", produces = "application/json")
  public User login(@RequestBody User credentials,
                    HttpSession session) {
    for (User user : users) {
      if (user.getUsername().equals(credentials.getUsername())
          && user.getPassword().equals(credentials.getPassword())) {
        session.setAttribute("currentUser", user);
        return user;
      }
    }
    return new User();
  }


  //Remove the current user from the session
  @PostMapping(path = "/api/logout", consumes = "application/json", produces = "application/json")
  public void logout
  (HttpSession session) {
    User currentUser = (User)
        session.getAttribute("currentUser");
    session.invalidate();
  }

}
