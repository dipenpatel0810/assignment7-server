package com.example.wbdvassignment5javaserver.services;

import com.example.wbdvassignment5javaserver.models.Course;
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
public class ModuleService {
  List<Course> courses = CourseService.getCourses();

  //  create a new module in course whose id is cid
  @PostMapping(path = "/api/courses/{cid}/modules", consumes = "application/json", produces = "application/json")
  public Module createModule(@PathVariable("cid") Integer cid, @RequestBody Module module) {
    Date uniqueId = new Date();
    int id = (int) (uniqueId.getTime() / 1000);
    module.setId(id);
    for (Course course : courses) {
      if (course.getId().equals(cid)) {
        course.getModules().add(module);
        return module;
      }
    }
    return new Module();
  }

  //Retrieve all modules in course whose id is cid
  @GetMapping("/api/courses/{cid}/modules")
  public List<Module> findAllModules(@PathVariable("cid") Integer cid) {
    for (Course course : courses) {
      if (course.getId().equals(cid)) {
        return course.getModules();
      }
    }
    return null;
  }

  //Retrieve module whose id is mid
  @GetMapping("/api/modules/{mid}")
  public Module findModuleById(@PathVariable("mid") Integer mid) {
    for (Course course : courses) {
      for (Module module : course.getModules()) {
        if (module.getId().equals(mid)) {
          return module;
        }
      }
    }
    return new Module();
  }

  //Update module whose id is mid
  @PutMapping(path = "/api/modules/{mid}", consumes = "application/json", produces = "application/json")
  public void updateModule(@PathVariable("mid") Integer mid, @RequestBody Module newModule) {
    for (Course course : courses) {
      List<Module> modules = course.getModules();
      for (Module module : modules) {
        if (module.getId().equals(mid)) {
          int index = modules.indexOf(module);
          modules.set(index, newModule);
          course.setModules(modules);
        }
      }
    }
  }

  //Delete a module whose id is mid
  @DeleteMapping("/api/modules/{mid}")
  public void deleteModule(@PathVariable("mid") Integer mid) {
    for (Course course : courses) {
      List<Module> modules = course.getModules();
      for (Iterator<Module> module = modules.iterator(); module.hasNext(); ) {
        Module m = module.next();
        if (m.getId().equals(mid)) {
          module.remove();
        }
      }
    }
  }


}
