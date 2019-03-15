package com.example.assignment6server.services;

import com.example.assignment6server.models.Course;
import com.example.assignment6server.models.Lesson;
import com.example.assignment6server.models.Module;
import com.example.assignment6server.models.Topic;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.CourseRepository;
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
public class ModuleService {
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

  @PostMapping("/api/courses/{cid}/modules")
  public Module createModule(@PathVariable("cid") Integer cid, @RequestBody Module module) {
    module.setCourse(courseRepository.findById(cid).get());
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
    return module;
  }

  @GetMapping("/api/courses/{cid}/modules")
  public List<Module> findAllModules(@PathVariable("cid") Integer cid) {
    Course course = courseRepository.findById(cid).get();
    return moduleRepository.findAllModules(course);
  }

  @GetMapping("/api/modules/{mid}")
  public Module findModuleById(@PathVariable("mid") Integer mid) {
    return moduleRepository.findById(mid).get();
  }

  @PutMapping("/api/modules/{mid}")
  public Module updateModule(@RequestBody Module newModule, @PathVariable("mid") Integer mid) {
    Module module = moduleRepository.findById(mid).get();
    module.set(newModule);
    moduleRepository.save(module);
    return module;
  }

  @DeleteMapping("/api/modules/{mid}")
  public void deleteModule(@PathVariable("mid") Integer mid) {
    Module module = moduleRepository.findById(mid).get();
    if (module.getLessons() != null) {
      for (Lesson lesson : module.getLessons()) {
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
        lessonRepository.delete(lesson);
      }
    }
    moduleRepository.deleteById(mid);
  }

}
