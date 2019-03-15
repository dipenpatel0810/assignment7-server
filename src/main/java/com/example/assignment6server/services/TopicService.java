package com.example.wbdvassignment5javaserver.services;

import com.example.wbdvassignment5javaserver.models.Course;
import com.example.wbdvassignment5javaserver.models.Lesson;
import com.example.wbdvassignment5javaserver.models.Module;
import com.example.wbdvassignment5javaserver.models.Topic;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
public class TopicService {
  List<Course> courses = CourseService.getCourses();

  //create new topic in lesson whose id is lid
  @PostMapping("/api/lesson/{lid}/topic")
  public Topic createTopic(@PathVariable("lid") Integer lid, @RequestBody Topic topic) {
    Date uniqueId = new Date();
    int id = (int)(uniqueId.getTime() / 1000);
    topic.setId(id);
    for (Course course : courses) {
      for (Module module : course.getModules()) {
        for (Lesson lesson : module.getLessons()) {
          if (lesson.getId().equals(lid)) {
            lesson.getTopics().add(topic);
            return topic;
          }
        }
      }
    }
    return new Topic();
  }

  //Retrieve all topics under lesson whose id is lid
  @GetMapping("/api/lesson/{lid}/topic")
  public List<Topic> findAllTopics(@PathVariable("lid") Integer lid) {
    for (Course course : courses) {
      for (Module module : course.getModules()) {
        for (Lesson lesson : module.getLessons()) {
          if (lesson.getId().equals(lid)) {
            return lesson.getTopics();
          }
        }
      }
    }
    return null;
  }

  // Retrieve topic whose id is tid
  @GetMapping("/api/topic/{tid}")
  public Topic findTopicById(@PathVariable("tid") Integer tid) {
    for (Course course : courses) {
      for (Module module : course.getModules()) {
        for (Lesson lesson : module.getLessons()) {
          for (Topic topic : lesson.getTopics()) {
            if (topic.getId().equals(tid)) {
              return topic;
            }
          }
        }
      }
    }
    return new Topic();
  }

  //Updates the topic whose id is tid
  @PutMapping("/api/topic/{tid}")
  public void updateTopic(@PathVariable("tid") Integer tid, @RequestBody Topic newTopic) {
    for (Course course : courses) {
      List<Module> modules = course.getModules();
      for (Module module : modules) {
        List<Lesson> lessons = module.getLessons();
        for (Lesson lesson : lessons) {
          List<Topic> topics = lesson.getTopics();
          for (Topic topic : topics) {
            if (topic.getId().equals(tid)) {
              int index = topics.indexOf(topic);
              topics.set(index, newTopic);
              lesson.setTopics(topics);
              module.setLessons(lessons);
              course.setModules(modules);
            }
          }
        }
      }
    }
  }

  //Deletes a topic whose id is tid
  @DeleteMapping("/api/topic/{tid}")
  public void deleteTopic(@PathVariable("tid") Integer tid) {
    for (Course course : courses) {
      List<Module> modules = course.getModules();
      for (Module module : modules) {
        List<Lesson> lessons = module.getLessons();
        for (Lesson lesson : lessons) {
          List<Topic> topics = lesson.getTopics();
          for (Iterator<Topic> topic = topics.iterator(); topic.hasNext(); ) {
            Topic t = topic.next();
            if (t.getId().equals(tid)) {
              topic.remove();
            }
          }
        }
      }
    }
  }
}
