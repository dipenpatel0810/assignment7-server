package com.example.assignment6server.services;

import com.example.assignment6server.models.LinkWidget;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.LinkWidgetRepository;
import com.example.assignment6server.repositories.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class LinkWidgetService {
  @Autowired
  TopicRepository topicRepository;

  @Autowired
  LinkWidgetRepository linkWidgetRepository;

  @PostMapping("/api/topics/{tid}/link/widgets")
  public LinkWidget createWidget(@PathVariable("tid") Integer tid, @RequestBody LinkWidget widget) {
    widget.setTopic(topicRepository.findById(tid).get());
    linkWidgetRepository.save(widget);
    return widget;
  }

  @GetMapping("/api/topics/{tid}/link/widgets")
  public List<LinkWidget> findAllWidgets(@PathVariable("tid") Integer tid) {
    return (List<LinkWidget>) linkWidgetRepository.findAll();
  }

  @GetMapping("/api/link/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid")Integer wid){
    return linkWidgetRepository.findById(wid).get();
  }

  @PutMapping("/api/link/widgets/{wid}")
  public LinkWidget updateWidget(@PathVariable("wid") Integer wid,@RequestBody LinkWidget newWidget){
    LinkWidget widget = linkWidgetRepository.findById(wid).get();
    widget.set(newWidget);
    linkWidgetRepository.save(newWidget);
    return widget;
  }

  @DeleteMapping("/api/link/widgets/{wid}")
  public void deleteWidget(@PathVariable("wid") Integer wid){
    linkWidgetRepository.deleteById(wid);
  }
}

