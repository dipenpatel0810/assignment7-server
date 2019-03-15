package com.example.assignment6server.services;
import com.example.assignment6server.models.Course;
import com.example.assignment6server.models.Lesson;
import com.example.assignment6server.models.Module;
import com.example.assignment6server.models.Topic;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.LessonRepository;
import com.example.assignment6server.repositories.TopicRepository;
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

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class TopicService {
  @Autowired
  LessonRepository lessonRepository;

  @Autowired
  TopicRepository topicRepository;

  @Autowired
  WidgetRepository widgetRepository;

  @PostMapping("/api/lessons/{lid}/topics")
  public Topic createTopic(@PathVariable("lid") Integer lid, @RequestBody Topic topic) {
    topic.setLesson(lessonRepository.findById(lid).get());
    topicRepository.save(topic);
    return topic;
  }

  @GetMapping("/api/lessons/{lid}/topics")
  public List<Topic> findAllTopics(@PathVariable("lid") Integer lid) {
    Lesson lesson = lessonRepository.findById(lid).get();
    return topicRepository.findAllTopics(lesson);
  }

  @GetMapping("/api/topics/{tid}")
  public Topic findTopicById(@PathVariable("tid") Integer tid) {
    return topicRepository.findById(tid).get();
  }

  @PutMapping("/api/topics/{tid}")
  public Topic updateTopic(@RequestBody Topic newTopic, @PathVariable("tid") Integer tid) {
    Topic topic = topicRepository.findById(tid).get();
    topic.set(newTopic);
    topicRepository.save(topic);
    return topic;
  }

  @DeleteMapping("/api/topics/{tid}")
  public void deleteTopic(@PathVariable("tid") Integer tid) {
    Topic topic = topicRepository.findById(tid).get();
    if (topic.getWidgets() != null) {
      for (Widget widget : topic.getWidgets()) {
        widgetRepository.delete(widget);
      }
    }
    topicRepository.deleteById(tid);
  }
}
