package com.example.wbdvassignment5javaserver.services;

import com.example.wbdvassignment5javaserver.models.Course;
import com.example.wbdvassignment5javaserver.models.Lesson;
import com.example.wbdvassignment5javaserver.models.Module;

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
public class LessonService {
  List<Course> courses = CourseService.getCourses();

  //create new lesson in module whose id is mid
  @PostMapping("/api/module/{mid}/lesson")
  public Lesson createLesson(@PathVariable("mid") Integer mid, @RequestBody Lesson lesson) {
    Date uniqueId = new Date();
    int id = (int)(uniqueId.getTime() / 1000);
    lesson.setId(id);
    for (Course course : courses) {
      for (Module module : course.getModules()) {
        if (module.getId().equals(mid)) {
          module.getLessons().add(lesson);
          return lesson;
        }
      }
    }
    return new Lesson();
  }

  //Retrieve all lessons in module whose id is mid
  @GetMapping("/api/module/{mid}/lesson")
  public List<Lesson> findAllLessons(@PathVariable("mid") Integer mid) {
    for (Course course : courses) {
      for (Module module : course.getModules()) {
        if (module.getId().equals(mid)) {
          return module.getLessons();
        }
      }
    }
    return null;
  }

  //Retrieve the lesson whose id is lid
  @GetMapping("/api/lesson/{lid}")
  public Lesson findLessonById(@PathVariable("lid") Integer lid) {
    for (Course course : courses) {
      for (Module module : course.getModules()) {
        for (Lesson lesson : module.getLessons()) {
          if (lesson.getId().equals(lid)) {
            return lesson;
          }
        }
      }
    }
    return new Lesson();
  }

  //update lesson whose id is lid
  @PutMapping("/api/lesson/{lid}")
  public void updateLesson(@PathVariable("lid") Integer lid, @RequestBody Lesson newLesson) {
    for (Course course : courses) {
      List<Module> modules = course.getModules();
      for (Module module : modules) {
        List<Lesson> lessons = module.getLessons();
        for (Lesson lesson : lessons) {
          if (lesson.getId().equals(lid)) {
            int index = lessons.indexOf(lesson);
            lessons.set(index, newLesson);
            module.setLessons(lessons);
            course.setModules(modules);
          }
        }
      }
    }
  }

  //deletes a lesson whose id is lid
  @DeleteMapping("/api/lesson/{lid}")
  public void deleteLesson(@PathVariable("lid") Integer lid) {
    for (Course course : courses) {
      List<Module> modules = course.getModules();
      for (Module module : modules) {
        List<Lesson> lessons = module.getLessons();
        for (Iterator<Lesson> lesson = lessons.iterator(); lesson.hasNext(); ) {
          Lesson l = lesson.next();
          if (l.getId().equals(lid)) {
            lesson.remove();
          }
        }
      }
    }
  }

}
