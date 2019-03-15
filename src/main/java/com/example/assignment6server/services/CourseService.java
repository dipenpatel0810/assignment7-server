package com.example.wbdvassignment5javaserver.services;

import com.example.wbdvassignment5javaserver.models.Course;
import com.example.wbdvassignment5javaserver.models.Lesson;
import com.example.wbdvassignment5javaserver.models.Module;
import com.example.wbdvassignment5javaserver.models.Topic;
import com.example.wbdvassignment5javaserver.models.User;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class CourseService {
  public CourseService() {
  }

  Course cs5610 = new Course(123, "cs5610");

  static List<Course> courses = new ArrayList<Course>();

  Module cs5610W1 = new Module(98, "Week 1");
  Module cs5610W2 = new Module(99, "Week 2");

  List<Module> cs5610modules = new ArrayList<Module>();

  Lesson cs5610W1Lesson = new Lesson(198, "Lesson 1");
  Lesson cs5610W2Lesson = new Lesson(199, "Lesson 2");

  List<Lesson> cs5610W1Lessons = new ArrayList<>();
  List<Lesson> cs5610W2Lessons = new ArrayList<>();

  Topic cs5610W1Lesson1Topic = new Topic(298, "Topic 1");

  List<Topic> cs5610W1Lesson1Topics = new ArrayList<>();

  {
    cs5610W1Lesson1Topics.add(cs5610W1Lesson1Topic);

    cs5610W1Lesson.setTopics(cs5610W1Lesson1Topics);

    cs5610W1Lessons.add(cs5610W1Lesson);
    cs5610W2Lessons.add(cs5610W2Lesson);

    cs5610W1.setLessons(cs5610W1Lessons);
    cs5610W2.setLessons(cs5610W2Lessons);

    cs5610modules.add(cs5610W1);
    cs5610modules.add(cs5610W2);

    cs5610.setModules(cs5610modules);
    cs5610.setAuthor("dipen");
    this.courses.add(cs5610);
  }

  public static List<Course> getCourses() {
    return courses;
  }

  public static void setCourses(List<Course> courses) {
    CourseService.courses = courses;
  }

  //Course

  //create course with currently logged in faculty as its author
  @PostMapping(path = "/api/courses", consumes = "application/json", produces = "application/json")
  public Course createCourse(@RequestBody Course course, HttpSession session) {
    Date uniqueId = new Date();
    int id = (int) (uniqueId.getTime() / 1000);
    course.setId(id);
    User author = (User)
        session.getAttribute("currentUser");
    course.setAuthor(author.getUsername());
    courses.add(course);
    return course;
  }

  //Retrieves all courses authored by the currently logged in author
  @GetMapping("/api/courses")
  public List<Course> findAllCourses(HttpSession session) {
    User author = (User)
        session.getAttribute("currentUser");
    List<Course> filteredCourses = new ArrayList<>();
    for (Course course : courses) {
      if (course.getAuthor().equals(author.getUsername())) {
        filteredCourses.add(course);
      }
    }
    return filteredCourses;
  }

  //Retrieves course whose id is cid
  @GetMapping("/api/courses/{cid}")
  public Course findCourseById(@PathVariable("cid") Integer cid) {
    for (Course course : this.courses) {
      if (course.getId().equals(cid)) {
        return course;
      }
    }
    return null;
  }

  //Updated course whose id is cid
  @PutMapping(path = "/api/courses/{cid}", consumes = "application/json", produces = "application/json")
  public void updateCourse(@PathVariable("cid") Integer cid, @RequestBody Course newCourse) {
    for (Course course : this.courses) {
      if (course.getId().equals(cid)) {
        int index = this.courses.indexOf(course);
        this.courses.set(index, newCourse);
      }
    }
  }

  //Removes course whose id is cid
  @DeleteMapping("/api/courses/{cid}")
  public List<Course> deleteCourse(@PathVariable("cid") Integer cid) {
    for (Iterator<Course> course = courses.iterator(); course.hasNext(); ) {
      Course c = course.next();
      if (c.getId().equals(cid)) {
        course.remove();
        return courses;
      }
    }
    return new ArrayList<Course>();
  }

}