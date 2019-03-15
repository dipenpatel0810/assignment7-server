package com.example.assignment6server.services;

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
public class HeadingWidgetService {
  @Autowired
  TopicRepository topicRepository;

  @Autowired
  HeadingWidgetRepository headingWidgetRepository;

  @PostMapping("/api/topics/{tid}/heading/widgets")
  public Widget createWidget(@PathVariable("tid") Integer tid, @RequestBody HeadingWidget widget) {
    widget.setTopic(topicRepository.findById(tid).get());
    headingWidgetRepository.save(widget);
    return widget;
  }

  @GetMapping("/api/topics/{tid}/heading/widgets")
  public List<HeadingWidget> findAllWidgets(@PathVariable("tid") Integer tid) {
    return (List<HeadingWidget>) headingWidgetRepository.findAll();
  }

  @GetMapping("/api/heading/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid")Integer wid){
    return headingWidgetRepository.findById(wid).get();
  }

  @PutMapping("/api/heading/widgets/{wid}")
  public HeadingWidget updateWidget(@PathVariable("wid") Integer wid,@RequestBody HeadingWidget newWidget){
    HeadingWidget widget = headingWidgetRepository.findById(wid).get();
    widget.set(newWidget);
    headingWidgetRepository.save(newWidget);
    return widget;
  }

  @DeleteMapping("/api/heading/widgets/{wid}")
  public void deleteWidget(@PathVariable("wid") Integer wid){
    headingWidgetRepository.deleteById(wid);
  }
}

