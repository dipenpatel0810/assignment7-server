package com.example.assignment6server.services;

import com.example.assignment6server.models.Course;
import com.example.assignment6server.models.Lesson;
import com.example.assignment6server.models.Module;
import com.example.assignment6server.models.Topic;
import com.example.assignment6server.models.User;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.CourseRepository;
import com.example.assignment6server.repositories.LessonRepository;
import com.example.assignment6server.repositories.ModuleRepository;
import com.example.assignment6server.repositories.TopicRepository;
import com.example.assignment6server.repositories.UserRepository;
import com.example.assignment6server.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  UserRepository userRepository;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  ModuleRepository moduleRepository;

  @Autowired
  LessonRepository lessonRepository;

  @Autowired
  TopicRepository topicRepository;

  @Autowired
  WidgetRepository widgetRepository;

  @PostMapping("/api/courses")
  public Course createCourse(@RequestBody Course course, HttpSession session) {
    User author = (User) session.getAttribute("currentUser");
    course.setAuthor(author);
    System.out.println(course.getId());
    System.out.println(course.getTitle());
    courseRepository.save(course);
    if (course.getModules() != null) {
      for (Module module : course.getModules()) {
        module.setCourse(course);
        moduleRepository.save(module);
        if (module.getLessons() != null) {
          for (Lesson lesson : module.getLessons()) {
            lesson.setModule(module);
            lessonRepository.save(lesson);
            if (lesson.getTopics() != null) {
              for (Topic topic : lesson.getTopics()) {
                topic.setLesson(lesson);
                topicRepository.save(topic);
              }
            }
          }
        }
      }
    }
    return course;
  }

  @GetMapping("/api/courses")
  public List<Course> findAllCourses(HttpSession session) {
    User author = (User) session.getAttribute("currentUser");
    return courseRepository.findAllCourses(author);
  }

  @GetMapping("/api/courses/{cid}")
  public Course findCourseById(@PathVariable("cid") Integer cid) {
    return courseRepository.findById(cid).get();
  }

  @PutMapping("/api/courses/{cid}")
  public Course updateCourse(@PathVariable("cid") Integer cid, @RequestBody Course newCourse) {
    Course course = courseRepository.findById(cid).get();
    course.set(newCourse);
    courseRepository.save(course);
    return course;
  }

  @DeleteMapping("/api/courses/{cid}")
  public void deleteCourse(@PathVariable("cid") Integer cid) {
    Course course = courseRepository.findById(cid).get();
    if (course.getModules()!= null){
      for (Module module : course.getModules()){
        if (module.getLessons()!=null){
          for (Lesson lesson : module.getLessons()){
            if (lesson.getTopics()!=null){
              for (Topic topic: lesson.getTopics()){
                if (topic.getWidgets()!=null){
                  for (Widget widget : topic.getWidgets()){
                    widgetRepository.delete(widget);
                  }
                }
                topicRepository.delete(topic);
              }
            }
            lessonRepository.delete(lesson);
          }
        }
        moduleRepository.delete(module);
      }
    }
    courseRepository.deleteById(cid);
  }
}
