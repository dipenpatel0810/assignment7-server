package com.example.assignment6server.services;

import com.example.assignment6server.models.Course;
import com.example.assignment6server.models.Lesson;
import com.example.assignment6server.models.Module;
import com.example.assignment6server.models.Topic;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.LessonRepository;
import com.example.assignment6server.repositories.ModuleRepository;
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
public class LessonService {
  @Autowired
  ModuleRepository moduleRepository;

  @Autowired
  LessonRepository lessonRepository;

  @Autowired
  TopicRepository topicRepository;

  @Autowired
  WidgetRepository widgetRepository;

  @PostMapping("/api/modules/{mid}/lessons")
  public Lesson createLesson(@RequestBody Lesson lesson, @PathVariable("mid") Integer mid) {
    lesson.setModule(moduleRepository.findById(mid).get());
    lessonRepository.save(lesson);
    if (lesson.getTopics() != null) {
      for (Topic topic : lesson.getTopics()) {
        topic.setLesson(lesson);
        topicRepository.save(topic);
      }
    }
    return lesson;
  }

  @GetMapping("/api/modules/{mid}/lessons")
  public List<Lesson> findAllLessons(@PathVariable("mid") Integer mid) {
    Module module = moduleRepository.findById(mid).get();
    return lessonRepository.findAllLessons(module);
  }

  @GetMapping("/api/lessons/{lid}")
  public Lesson findLessonById(@PathVariable("lid") Integer lid) {
    return lessonRepository.findById(lid).get();
  }

  @PutMapping("/api/lessons/{lid}")
  public Lesson updateLesson(@PathVariable("lid") Integer lid, @RequestBody Lesson newLesson) {
    Lesson lesson = lessonRepository.findById(lid).get();
    lesson.set(newLesson);
    lessonRepository.save(lesson);
    return lesson;
  }

  @DeleteMapping("/api/lessons/{lid}")
  public void deleteLesson(@PathVariable("lid") Integer lid) {
    Lesson lesson = lessonRepository.findById(lid).get();
    if (lesson.getTopics() != null) {
      for (Topic topic : lesson.getTopics()) {
        if (topic.getWidgets() != null) {
          for (Widget widget : topic.getWidgets()) {
            widgetRepository.delete(widget);
          }
        }
        topicRepository.delete(topic);
      }
    }

    lessonRepository.deleteById(lid);
  }
}
